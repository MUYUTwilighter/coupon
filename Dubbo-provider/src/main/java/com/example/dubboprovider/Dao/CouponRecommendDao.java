package com.example.dubboprovider.Dao;

import com.example.dubboprovider.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CouponRecommendDao extends JpaRepository<Coupon, Long>{
    @Query(value = "SELECT * FROM coupon WHERE coupon.end > CURRENT_TIMESTAMP AND coupon.start < CURRENT_TIMESTAMP limit 20",nativeQuery=true)
    List<Coupon> recommendCoupons();


}
