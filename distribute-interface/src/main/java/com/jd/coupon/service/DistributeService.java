package com.jd.coupon.service;


import com.jd.coupon.entity.CouponDto;
import com.sun.istack.NotNull;
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

    /**
     * Search up to 10 available(today) coupons which match required condition
     * @param hobby customers' hobby
     * @param job   customers' job
     * @param page  page switch
     * @return A list of coupons available today that matches search args
     * */
    List<CouponDto> searchInterest(@NotNull String hobby, @NotNull String job, @Nullable Integer page);
}
