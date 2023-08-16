package com.example.dubboprovider.service;

//import com.exercise.mapper.CouponMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface CouponDistributionService {
    String distributeCoupons(String hobby) throws JsonProcessingException;
}
