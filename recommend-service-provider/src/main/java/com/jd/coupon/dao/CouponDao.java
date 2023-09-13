package com.jd.coupon.dao;

import com.jd.coupon.entity.Coupon;
import com.jd.coupon.key.CouponId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponDao extends JpaRepository<Coupon, CouponId> {
}
