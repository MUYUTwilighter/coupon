package com.jd.coupon.service;

import com.jd.coupon.entity.Coupon;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import java.util.List;

/**
 * @author MUYU_Twilighter
 */
public interface DistributeService {
    List<Coupon> distributeInterest(@NotNull String hobby, @NotNull String job, @Nullable Integer count);
}
