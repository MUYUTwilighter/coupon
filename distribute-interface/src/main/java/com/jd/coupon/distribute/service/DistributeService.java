package com.jd.coupon.distribute.service;

import com.jd.coupon.distribute.entity.CouponDto;
import com.sun.istack.Nullable;

import java.util.List;

/**
 * @author MUYU_Twilighter
 */
public interface DistributeService {
    /**
     * Search up to 10 coupons which are available today
     *
     * @param business  business the coupon belongs to, support vague search
     * @param page      page switch, default {@code 0} means 1-10, {@code 1} means 11-20, etc.
     *
     * @return A list of coupons available today that matches search args
     * */
    List<CouponDto> searchAvailable(@Nullable String business, @Nullable Integer page);
}
