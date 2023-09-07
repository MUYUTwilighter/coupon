package com.jd.coupon.controller;

import com.jd.coupon.entity.Coupon;
import com.jd.coupon.entity.CouponRequest;
import com.jd.coupon.entity.StaffRequestDto;
import com.jd.coupon.service.CouponRequestService;
import com.jd.coupon.service.CouponService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * @author MUYU_Twilighter
 */
@RestController
@RequestMapping("coupon")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @GetMapping("search")
    public List<Coupon> search(@RequestParam(required = false) String business,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) Byte type,
                               @RequestParam(required = false) BigDecimal minValue,
                               @RequestParam(required = false) BigDecimal maxValue,
                               @RequestParam(required = false) BigDecimal minLimitValue,
                               @RequestParam(required = false) BigDecimal maxLimitValue,
                               @RequestParam(required = false) Integer remain,
                               @RequestParam(required = false) Integer total,
                               @RequestParam(required = false) Date start,
                               @RequestParam(required = false) Date end,
                               @RequestParam(required = false) Integer page) {
        return couponService.search(business, name, type, minValue, maxValue, minLimitValue, maxLimitValue,
            remain, total, start, end, page);
    }

    @RestController
    @RequestMapping("coupon/request")
    public static class CouponRequestController {
        @Autowired
        private CouponService couponService;

        @PostMapping("create")
        public void create(@RequestParam String initiator,
                           @RequestParam String business,
                           @RequestParam String name,
                           @RequestParam Byte type,
                           @RequestParam BigDecimal value,
                           @RequestParam BigDecimal limitValue,
                           @RequestParam Integer count,
                           @RequestParam Date start,
                           @RequestParam Date end) {
            this.couponService.create(initiator, business, name, type, value, limitValue,
                count, start, end);
        }

        @PostMapping("delete")
        public void delete(@RequestParam String initiator,
                           @RequestParam String business,
                           @RequestParam String name) {
            this.couponService.delete(initiator, business, name);
        }

        @PostMapping("post")
        public void post(@RequestParam String initiator,
                         @RequestParam String business,
                         @RequestParam String name,
                         @RequestParam Integer count) {
            this.couponService.post(initiator, business, name, count);
        }

        @PostMapping("withdraw")
        public void withdraw(@RequestParam String initiator,
                             @RequestParam String business,
                             @RequestParam String name,
                             @RequestParam Integer count) {
            this.couponService.withdraw(initiator, business, name, count);
        }
    }

    @RestController
    @RequestMapping("coupon/approve")
    public static class CouponApproveController {
        @Autowired
        private CouponRequestService couponRequestService;

        @GetMapping("search")
        public List<CouponRequest> search(@RequestParam(required = false) Byte category,
                                            @RequestParam(required = false) Date start,
                                            @RequestParam(required = false) Date end,
                                            @RequestParam(required = false) String initiator,
                                            @RequestParam(required = false) Boolean rejected,
                                            @RequestParam(required = false) Byte nextApprove,
                                            @RequestParam(required = false) Integer page) {
            return couponRequestService.search(category, start, end, initiator, rejected, nextApprove, page);
        }

        @GetMapping("pending/{name}")
        public List<CouponRequest> searchSelf(@PathVariable(name = "name") String name,
                                              @RequestParam(required = false) Byte category,
                                              @RequestParam(required = false) Date start,
                                              @RequestParam(required = false) Date end,
                                              @RequestParam(required = false) Boolean rejected,
                                              @RequestParam(required = false) Byte nextApprove,
                                              @RequestParam(required = false) Integer page) {
            return couponRequestService.search(category, start, end, name, rejected, nextApprove, page);
        }

        @GetMapping("{id}")
        public CouponRequest find(@PathVariable(name = "id") Long id) {
            return couponRequestService.find(id);
        }

        @PostMapping("{id}/process")
        public void approve(HttpServletRequest request, @PathVariable(name = "id") Long id) {
            Byte auth = Byte.valueOf(request.getParameter("auth"));
            couponRequestService.approve(id, auth);
        }
    }
}
