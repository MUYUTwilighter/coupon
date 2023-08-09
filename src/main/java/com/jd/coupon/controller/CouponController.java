package com.jd.coupon.controller;

import com.jd.coupon.component.AuthComponent;
import com.jd.coupon.component.CouponRequestComponent;
import com.jd.coupon.entity.Coupon;
import com.jd.coupon.entity.Staff;
import com.jd.coupon.exception.BadArgumentException;
import com.jd.coupon.exception.PermissionDenyException;
import com.jd.coupon.request.CouponRequest;
import com.jd.coupon.service.CouponService;
import com.jd.coupon.service.StaffService;
import com.jd.coupon.util.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author MUYU_Twilighter
 */
@RestController
@RequestMapping("coupon")
@Api("Coupon management interfaces")
public class CouponController {
    @Autowired
    private CouponService couponService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private CouponRequestComponent couponComponent;
    @Autowired
    private AuthComponent authComponent;

    /* PASSED */
    @PostMapping("post/request")
    @ApiOperation("Post a request to post coupons")
    @ApiResponse(description = "return true if request initiated")
    public Boolean postRequest(
        @RequestParam("token") @ApiParam("token") String token,
        @RequestParam("business") @ApiParam("business the coupon(s) belong to") String business,
        @RequestParam("name") @ApiParam("coupon name") String name,
        @RequestParam("count") @ApiParam("count") Integer count,
        @RequestParam(value = "type", required = false) @ApiParam("coupon type") Short type,
        @RequestParam(value = "value", required = false) @ApiParam("coupon value") BigDecimal value,
        @RequestParam(value = "limit", required = false) @ApiParam("coupon limit") BigDecimal limit,
        @RequestParam(value = "start", required = false) @ApiParam("start date") Date start,
        @RequestParam(value = "end", required = false) @ApiParam("end date") Date end) {
        String operatorName = authComponent.find(token);
        Staff staff = staffService.find(operatorName);
        if (staff == null || !staff.beyond(Staff.AUTH_STAFF)) {
            throw PermissionDenyException.INSTANCE;
        }
        if (!staff.beyond(Staff.AUTH_SYS_ADMIN) && !Objects.equals(staff.getBusiness(), business)) {
            throw PermissionDenyException.INSTANCE;
        }
        if (couponService.exists(business, name)) {
            if (ObjectUtil.anyNotNull(value, limit, start, end)) {
                throw BadArgumentException.INSTANCE;
            }
        } else {
            if (ObjectUtil.anyNull(value, limit, start, end)) {
                throw BadArgumentException.INSTANCE;
            }
        }

        Coupon coupon = new Coupon();
        coupon.setBusiness(business);
        coupon.setName(name);
        coupon.setType(type);
        coupon.setRemain(count);
        coupon.setTotal(count);
        coupon.setValue(value);
        coupon.setLimitValue(limit);
        coupon.setStart(start);
        coupon.setEnd(end);

        CouponRequest request = new CouponRequest();
        request.setInitiator(operatorName);
        request.setCategory(CouponRequest.POST);
        request.setApprove((short) 0);
        request.setCoupon(coupon);
        couponComponent.put(request);
        return true;
    }

    /* PASSED */
    @PostMapping("post/approve")
    @ApiOperation("Approve a request to post coupons")
    @ApiResponse(description = "return true if approval success")
    public Boolean postApprove(
        @RequestParam("token") @ApiParam("token") String token,
        @RequestParam("id") @ApiParam("request id") String id) {
        Staff operator = findByToken(token, Staff.AUTH_SYS_ADMIN);
        if (operator == Staff.EMPTY) {
            throw PermissionDenyException.INSTANCE;
        }
        CouponRequest request = couponComponent.find(id);
        if (request == null) {
            return false;
        }
        if (operator.beyond(Staff.AUTH_ADMIN) && request.getApprove() == 1) {
            Coupon coupon = request.getCoupon();
            String business = coupon.getBusiness();
            String name = coupon.getName();
            Short type = coupon.getType();
            BigDecimal value = coupon.getValue();
            BigDecimal limit = coupon.getLimitValue();
            Date start = coupon.getStart();
            Date end = coupon.getEnd();
            Integer count = coupon.getRemain();
            if (couponService.exists(business, name)) {
                if (ObjectUtil.allNull(value, limit, start, end, type)) {
                    couponService.post(business, name, count);
                    couponComponent.delete(id);
                    return true;
                }
            } else if (ObjectUtil.allNotNull(value, limit, start, end, type)) {
                couponService.post(business, name, type, count, value, limit, start, end);
                couponComponent.delete(id);
                return true;
            }
        } else if (request.getApprove() == 0) {
            return couponComponent.update(id, (short) 1);
        }
        return false;
    }

    /* PASSED */
    @GetMapping("request/list")
    @ApiOperation("Get a list of request to manage coupon(s)")
    @ApiResponse(description = "return a list of up to 10 requests, return empty if permission denied")
    public List<CouponRequest> requestList(
        @RequestParam("token") @ApiParam("token") String token,
        @RequestParam(value = "business", required = false) @ApiParam("business the coupon belongs to") String business,
        @RequestParam(value = "name", required = false) @ApiParam("name of the coupon") String name,
        @RequestParam(value = "initiator", required = false) @ApiParam("name of initiator") String initiator,
        @RequestParam(value = "page", defaultValue = "0") @ApiParam("page switch") Integer page) {
        Staff operator = findByToken(token, Staff.AUTH_SYS_ADMIN);
        if (operator == Staff.EMPTY) {
            return new ArrayList<>();
        }
        return couponComponent.query(page);
    }

    /* PASSED */
    @PostMapping("remove/request")
    @ApiOperation("Remove specific amount of coupon(s)")
    @ApiResponse(description = "return true if request initiated")
    public Boolean removeRequest(
        @RequestParam("token") @ApiParam(value = "token", required = true) String token,
        @RequestParam("business") @ApiParam(value = "business the coupon(s) belong to", required = true) String business,
        @RequestParam("name") @ApiParam(value = "coupon name", required = true) String name,
        @RequestParam(value = "count", required = false) @ApiParam("coupon count to remove, remove whole if empty") Integer count) {
        Staff operator = findByToken(token, Staff.AUTH_SYS_ADMIN);
        if (operator == Staff.EMPTY) {
            return false;
        }
        Coupon coupon = couponService.find(business, name);
        if (coupon == null) {
            return false;
        }
        if (count != null && count > coupon.getRemain()) {
            return false;
        }
        coupon.setRemain(count);
        coupon.setTotal(count);

        CouponRequest request = new CouponRequest();
        request.setInitiator(operator.getName());
        request.setCoupon(coupon);
        request.setCategory(CouponRequest.REMOVE);
        request.setApprove((short) 0);
        couponComponent.put(request);
        return true;
    }

    /* PASSED */
    @PutMapping("remove/approve")
    @ApiOperation("Approve a request to remove coupons")
    @ApiResponse(description = "return true if approval success")
    public Boolean removeApprove(
        @RequestParam("token") @ApiParam(value = "token", required = true) String token,
        @RequestParam("id") @ApiParam(value = "request id", required = true) String id) {
        Staff operator = findByToken(token, Staff.AUTH_SYS_ADMIN);
        if (operator == Staff.EMPTY) {
            return false;
        }
        CouponRequest request = couponComponent.find(id);
        if (request == null) {
            return false;
        }
        if (request.getApprove() == 0) {
            couponComponent.update(id, (short) 1);
            return true;
        }
        if (request.getApprove() == 1 && operator.beyond(Staff.AUTH_ADMIN)) {
            Coupon coupon = request.getCoupon();
            couponService.remove(coupon.getBusiness(), coupon.getName(), coupon.getRemain());
            couponComponent.delete(id);
            return true;
        }
        return false;
    }

    /* PASSED */
    @GetMapping("search")
    @ApiOperation("Get a list of request to remove coupon(s)")
    @ApiResponse(description = "return a list of up to 10 coupons, return empty if permission denied")
    public List<Coupon> search(
        @RequestParam("token") @ApiParam(value = "token", required = true) String token,
        @RequestParam(value = "business", defaultValue = "%") @ApiParam("business of the coupon") String business,
        @RequestParam(value = "name", defaultValue = "%") @ApiParam("name of the coupon") String name,
        @RequestParam(value = "type", required = false) @ApiParam("type of the coupon") Short type,
        @RequestParam(value = "minValue", defaultValue = "0") @ApiParam("minimum value of coupon(s)") BigDecimal minValue,
        @RequestParam(value = "maxValue", defaultValue = "1000000") @ApiParam("maximum value of coupon(s)") BigDecimal maxValue,
        @RequestParam(value = "minLimit", defaultValue = "0") @ApiParam("minimum limit of coupon(s)") BigDecimal minLimit,
        @RequestParam(value = "maxLimit", defaultValue = "1000000") @ApiParam("maximum limit of coupon(s)") BigDecimal maxLimit,
        @RequestParam(value = "start", defaultValue = "1970-1-1") @ApiParam("after when the coupon expire") Date start,
        @RequestParam(value = "end", defaultValue = "2100-1-1") @ApiParam("before when the coupon take effect") Date end,
        @RequestParam(value = "page", defaultValue = "0") @ApiParam("page switch") Integer page) {
        Staff operator = findByToken(token, Staff.AUTH_STAFF);
        if (operator == Staff.EMPTY) {
            return new ArrayList<>();
        }
        if (!operator.beyond(Staff.AUTH_SYS_ADMIN)) {
            business = operator.getBusiness();
        }
        return couponService.search(business, name, type, minValue, maxValue, minLimit, maxLimit, start, end, page);
    }

    /* PASSED */
    @PutMapping("distribute")
    @ApiOperation("Notify distribution of a coupon")
    @ApiResponse(description = "return true if distribution valid")
    public Boolean distribute(
        @RequestParam("token") @ApiParam("token") String token,
        @RequestParam("business") @ApiParam("coupon business") String business,
        @RequestParam("name") @ApiParam("coupon name") String name,
        @RequestParam("count") @ApiParam("coupon count") Integer count) {
        findByToken(token, Staff.AUTH_STAFF);
        Coupon coupon = couponService.find(business, name);
        if (coupon == null) {
            return false;
        }
        if (coupon.getRemain() >= count) {
            couponService.distribute(coupon.getBusiness(), coupon.getName(), count);
            return true;
        } else {
            return false;
        }
    }

    private Staff findByToken(String token, Short auth) {
        String name = authComponent.find(token);
        if (name == null) {
            throw PermissionDenyException.INSTANCE;
        } else {
            Staff staff = staffService.find(name, auth);
            if (staff == Staff.EMPTY) {
                throw PermissionDenyException.INSTANCE;
            }
            return staff;
        }
    }
}
