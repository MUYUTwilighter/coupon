package com.exercise.serviceimpl;

import com.exercise.Dao.CouponDistributeDao;
import com.exercise.entity.RecommendCoupon;
import com.exercise.service.CouponDistributionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
@Service
public class CouponDistributionServiceImpl implements CouponDistributionService {

    @Autowired
    private CouponDistributeDao couponDistributionDao;

    @Override
    public String distributeCoupons(String hobby) throws JsonProcessingException {
        List<RecommendCoupon> filteredCoupons= couponDistributionDao.distributeCoupons(hobby);
        List<RecommendCoupon> distributedCoupons = new ArrayList<>();
        int maxCouponCount = 10;
        int count = Math.min(filteredCoupons.size(), maxCouponCount);
        for (int i = 0; i < count; i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(filteredCoupons.size());
            distributedCoupons.add(filteredCoupons.remove(randomIndex));
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(distributedCoupons);
    }
}
