package com.example.dubboconsume.Controller.Dao;

import com.example.dubboconsume.Controller.Dao.Others.Service.Coupon;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ReturnCoupon {

    void sendCoupons(List<Coupon> coupons) throws JsonProcessingException;
}
