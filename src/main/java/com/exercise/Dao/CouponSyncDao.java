package com.exercise.Dao;

import com.exercise.entity.Coupon;
import com.exercise.entity.RecommendCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CouponSyncDao extends JpaRepository<RecommendCoupon, Long> {
    RecommendCoupon findById(String id);

}