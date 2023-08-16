package com.example.dubboconsume.Controller.Dao;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.dubboconsume.Controller.Dao.Others.Service.Coupon;
import com.example.dubbointerface.ReturnCouponInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnCouponImp implements ReturnCoupon{

    @Reference(interfaceClass =ReturnCouponInterface.class)
    ReturnCouponInterface returnCouponInterface;
    @Override
    public void sendCoupons(List<Coupon> coupons) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(coupons);//优惠券list序列化
        returnCouponInterface.sendCoupons(json);//调用接口传入优惠券
    }

}
