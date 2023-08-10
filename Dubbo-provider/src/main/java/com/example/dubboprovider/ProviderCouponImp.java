package com.example.dubboprovider;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.dubbointerface.ProviderCoupon;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@Service(interfaceClass = ProviderCoupon.class,version = "1.0.0",timeout = 15000)
public class ProviderCouponImp implements ProviderCoupon, Serializable {
    @Override
    public String getCoupons(String habit) throws JsonProcessingException {
        List<Coupon> coupons = new ArrayList<>(); // 初始化列表
        for(int i=0;i<10;i++){
            Random random = new Random();
            int sale = random.nextInt(400)+100;
            Coupon coupon = new Coupon(sale,random.nextInt(500-sale)+sale);
            coupons.add(coupon);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(coupons);
        return json;
    }


}
