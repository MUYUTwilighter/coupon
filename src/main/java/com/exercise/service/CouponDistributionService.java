package com.exercise.service;

import com.exercise.entity.Coupon;
//import com.exercise.mapper.CouponMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public interface CouponDistributionService {
    String distributeCoupons(String hobby) throws JsonProcessingException;
}
