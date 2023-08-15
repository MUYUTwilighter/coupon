package com.exercise.Dao;

import com.exercise.entity.Coupon;
import com.exercise.entity.RecommendCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CouponDistributeDao extends JpaRepository<RecommendCoupon, Long> {
    @Query(value = "SELECT * FROM recommend_coupon WHERE business=?1 limit 20",nativeQuery=true)
    List<RecommendCoupon> distributeCoupons(String hobby);
}