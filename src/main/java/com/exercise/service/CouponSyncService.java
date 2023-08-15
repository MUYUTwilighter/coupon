package com.exercise.service;

import com.exercise.entity.Coupon;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public interface CouponSyncService {
    void syncAndSaveCoupons();
}
