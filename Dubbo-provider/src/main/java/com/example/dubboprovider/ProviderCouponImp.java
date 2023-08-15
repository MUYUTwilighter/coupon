package com.example.dubboprovider;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.dubbointerface.ProviderCoupon;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Service(interfaceClass = ProviderCoupon.class)
public class ProviderCouponImp implements ProviderCoupon, Serializable {

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

    //提供者
//    @Override
//    public String getCoupons(String habit) throws JsonProcessingException {
//        List<Coupon> coupons = new ArrayList<>(); // 初始化列表
//        for(int i=0;i<10;i++){
//            Random random = new Random();
//            int total = random.nextInt(400)+100;
//            Coupon coupon = new Coupon(total,random.nextInt(total-100)+100);
//            coupons.add(coupon);
//        }//生成一系列优惠券
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(coupons);//优惠券list序列化
//        return json;//服务方提供序列化string到消费者
////        return "Hello";
//    }


}
