package com.jd.coupon.service;

import com.jd.coupon.dao.CouponDao;
import com.jd.coupon.entity.Coupon;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * @author MUYU_Twilighter
 */
@DubboService
@Component
public class DistributeServiceImpl implements DistributeService {
    @Autowired
    CouponDao dao;

    public synchronized List<Coupon> distributeInterest(@NotNull String hobby, @NotNull String job, @Nullable Integer count) {
        count = (count == null || count < 0 || count > 10) ? 1 : count;
        Date now = Date.valueOf(LocalDate.now());
        List<Coupon> coupons = dao.searchAndReduce(hobby, job, now, now, 0, count);
        dao.saveAll(coupons);
        return coupons;
    }
}
