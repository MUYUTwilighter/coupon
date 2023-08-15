package com.exercise.controller;

import com.exercise.entity.Coupon;
//import com.exercise.service.CouponDistributionService;
import com.exercise.service.CouponDistributionService;
import com.exercise.service.CouponRecommendService;
import com.exercise.service.CouponSyncService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupon")
public class CouponController {
    @Autowired
    private CouponRecommendService couponRecommendService;

    @Autowired
    private CouponDistributionService couponDistributionService;
    @Autowired
    private CouponSyncService couponSyncService;

    @PostMapping("recommend")
    public List<Coupon> recommendCoupons(){
        List<Coupon> couponList= couponRecommendService.recommendCoupons();
        return couponList;
    }

    @PostMapping("sync")
    public void syncCoupons() {
        couponSyncService.syncAndSaveCoupons();
    }

    @PostMapping("distribute")
    @ResponseBody
    public String distributeCoupons(String hobby) throws JsonProcessingException {
        return couponDistributionService.distributeCoupons(hobby);
    }
}

