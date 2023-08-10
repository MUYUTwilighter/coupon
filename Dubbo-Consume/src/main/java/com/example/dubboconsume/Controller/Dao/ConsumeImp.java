package com.example.dubboconsume.Controller.Dao;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.dubboconsume.Controller.Dao.Others.Service.Coupon;
import com.example.dubboconsume.Controller.Dao.Others.User;
import com.example.dubbointerface.ProviderCoupon;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConsumeImp implements Consume {

    @Reference(interfaceClass = ProviderCoupon.class,version = "1.0.0",check = false)
    ProviderCoupon providerCoupon;
    @Override
    public List<Coupon> getCoupons(User user) throws JsonProcessingException {
        String habit = user.getHabit().name();
        String json = providerCoupon.getCoupons(habit);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Coupon> coupons = objectMapper.readValue(json, new TypeReference<List<Coupon>>() {});
        System.out.println("Succeed in call API.");
        return coupons;
    }
}
