package com.jd.coupon.service;

import com.jd.coupon.dao.CouponDao;
import com.jd.coupon.entity.CouponDto;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author MUYU_Twilighter
 */
@DubboService
@Component
public class DistributeServiceImpl implements DistributeService {
    @Autowired
    CouponDao dao;

    @Override
    public List<CouponDto> searchAvailable(@Nullable String business, @Nullable Integer page) {
        page = (page == null || page < 0) ? 0 : page;
        Date now = Date.valueOf(LocalDate.now());
        List<Map<String, Object>> results = this.dao.
            searchAvailable(business, null, null, null, null, null, null, now, now, page * 10);
        return (List<CouponDto>) (Object) results;
    }

    @Override
    public List<CouponDto> searchInterest(@NotNull String hobby, @NotNull String job, @Nullable Integer page) {
        page = (page == null || page < 0) ? 0 : page;
        Date now = Date.valueOf(LocalDate.now());
        List<Map<String, Object>> results = this.dao.
            searchInterest(hobby, job, now, now, page);
        return (List<CouponDto>) (Object) results;
    }
}
