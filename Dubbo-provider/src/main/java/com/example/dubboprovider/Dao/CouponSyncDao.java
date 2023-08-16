package com.example.dubboprovider.Dao;

import com.example.dubboprovider.entity.RecommendCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponSyncDao extends JpaRepository<RecommendCoupon, Long> {
    RecommendCoupon findById(String id);

}