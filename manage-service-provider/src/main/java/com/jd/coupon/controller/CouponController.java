package com.jd.coupon.controller;

import com.jd.coupon.entity.Coupon;
import com.jd.coupon.entity.CouponRequest;
import com.jd.coupon.service.CouponRequestService;
import com.jd.coupon.service.CouponService;
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
    public List<Coupon> search(@RequestParam(value = "business", required = false) String business,
                               @RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "type", required = false) Byte type,
                               @RequestParam(value = "min_value", required = false) BigDecimal minValue,
                               @RequestParam(value = "max_value", required = false) BigDecimal maxValue,
                               @RequestParam(value = "min_limit_value", required = false) BigDecimal minLimitValue,
                               @RequestParam(value = "max_limit_value", required = false) BigDecimal maxLimitValue,
                               @RequestParam(value = "remain", required = false) Integer remain,
                               @RequestParam(value = "total", required = false) Integer total,
                               @RequestParam(value = "start", required = false) Date start,
                               @RequestParam(value = "end", required = false) Date end,
                               @RequestParam(value = "usable_cate", required = false) Long usableCate,
                               @RequestParam(value = "page", required = false) Integer page) {
        return couponService.search(business, name, type, minValue, maxValue, minLimitValue, maxLimitValue,
            remain, total, start, end, usableCate, page);
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
                           @RequestParam Date end,
                           @RequestParam Long usableCate) {
            this.couponService.create(initiator, business, name, type, value, limitValue,
                count, start, end, usableCate);
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
        public List<CouponRequest> search(@RequestParam(value = "category", required = false) Byte category,
                                          @RequestParam(value = "start", required = false) Date start,
                                          @RequestParam(value = "end", required = false) Date end,
                                          @RequestParam(value = "initiator", required = false) String initiator,
                                          @RequestParam(value = "rejected", required = false) Boolean rejected,
                                          @RequestParam(value = "next_approve", required = false) Byte nextApprove,
                                          @RequestParam(value = "page", required = false) Integer page) {
            return couponRequestService.search(category, start, end, initiator, rejected, nextApprove, page);
        }

        @GetMapping("pending/{name}")
        public List<CouponRequest> searchSelf(@PathVariable(name = "name") String name,
                                              @RequestParam(value = "category", required = false) Byte category,
                                              @RequestParam(value = "start", required = false) Date start,
                                              @RequestParam(value = "end", required = false) Date end,
                                              @RequestParam(value = "rejected", required = false) Boolean rejected,
                                              @RequestParam(value = "next_approve", required = false) Byte nextApprove,
                                              @RequestParam(value = "page", required = false) Integer page) {
            return couponRequestService.search(category, start, end, name, rejected, nextApprove, page);
        }

        @GetMapping("{id}")
        public CouponRequest find(@PathVariable(value = "id") Long id) {
            return couponRequestService.find(id);
        }

        @PostMapping("{id}/process")
        public void approve(HttpServletRequest request, @PathVariable(name = "id") Long id) {
            Byte auth = Byte.valueOf(request.getParameter("auth"));
            couponRequestService.approve(id, auth);
        }
    }
}
