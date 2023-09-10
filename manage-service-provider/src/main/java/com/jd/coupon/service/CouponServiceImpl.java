package com.jd.coupon.service;

import com.jd.coupon.dao.CouponDao;
import com.jd.coupon.dao.CouponRequestDao;
import com.jd.coupon.entity.Coupon;
import com.jd.coupon.entity.CouponRequest;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * @author MUYU_Twilighter
 */
@DubboService
@Component
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponDao couponDao;
    @Autowired
    private CouponRequestDao couponRequestDao;

    public CouponRequest find(@NotNull Long id) {
        return couponRequestDao.find(id);
    }

    @Override
    public List<Coupon> search(@Nullable String business,
                               @Nullable String name,
                               @Nullable Byte type,
                               @Nullable BigDecimal minValue,
                               @Nullable BigDecimal maxValue,
                               @Nullable BigDecimal minLimit,
                               @Nullable BigDecimal maxLimit,
                               @Nullable Integer remain,
                               @Nullable Integer total,
                               @Nullable Date start,
                               @Nullable Date end,
                               @Nullable Long usableCate,
                               @Nullable Integer page) {
        page = page == null || page < 0 ? 0 : page;
        return couponDao.search(business, name, type, minValue, maxValue, minLimit, maxLimit, remain, total, start, end, page);
    }

    @Override
    public void create(@NotNull String initiator,
                       @NotNull String business,
                       @NotNull String name,
                       @NotNull Byte type,
                       @NotNull BigDecimal value,
                       @NotNull BigDecimal limitValue,
                       @NotNull Integer count,
                       @NotNull Date start,
                       @NotNull Date end,
                       @NotNull Long usableCate) {
        CouponRequest request = CouponRequest.initCreate(initiator, business, name, type, value, limitValue, count, start, end, usableCate);
        couponRequestDao.save(request);
    }

    @Override
    public void delete(@NotNull String initiator,
                       @NotNull String business,
                       @NotNull String name) {
        CouponRequest request = CouponRequest.initDelete(initiator, business, name);
        couponRequestDao.save(request);
    }

    @Override
    public void post(@NotNull String initiator,
                     @NotNull String business,
                     @NotNull String name,
                     @NotNull Integer count) {
        CouponRequest request = CouponRequest.initPost(initiator, business, name, count);
        couponRequestDao.save(request);
    }

    @Override
    public void withdraw(@NotNull String initiator,
                         @NotNull String business,
                         @NotNull String name,
                         @NotNull Integer count) {
        CouponRequest request = CouponRequest.initWithdraw(initiator, business, name, count);
        couponRequestDao.save(request);
    }
}
