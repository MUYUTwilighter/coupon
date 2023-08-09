package com.jd.coupon.service;

import com.jd.coupon.dao.CouponDao;
import com.jd.coupon.entity.Coupon;
import com.jd.coupon.key.CouponId;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author MUYU_Twilighter
 */
@Service
public class CouponServiceImpl implements CouponService {
    private CouponDao couponDao;

    @Override
    public Boolean exists(@NotNull String business, @NotNull String name) {
        CouponId id = CouponId.of(business, name);
        Optional<Coupon> optional = couponDao.findById(id);
        return optional.isPresent();
    }

    @Override
    public Boolean post(@NotNull String business, @NotNull String name, @NotNull Integer count) {
        CouponId id = CouponId.of(business, name);
        Optional<Coupon> optional = couponDao.findById(id);
        if (optional.isPresent()) {
            Coupon coupon = optional.get();
            coupon.setTotal(coupon.getTotal() + count);
            coupon.setRemain(coupon.getRemain() + count);
            couponDao.save(coupon);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean post(@NotNull String business, @NotNull String name, @NotNull Integer count,
                        @NotNull BigDecimal value, @NotNull BigDecimal limit,
                        @NotNull Date start, @NotNull Date end) {
        CouponId id = CouponId.of(business, name);
        Optional<Coupon> optional = couponDao.findById(id);
        if (optional.isPresent()) {
            return false;
        } else {
            Coupon coupon = new Coupon();
            coupon.setBusiness(business);
            coupon.setName(name);
            coupon.setRemain(count);
            coupon.setTotal(count);
            coupon.setValue(value);
            coupon.setLimitValue(limit);
            coupon.setStart(start);
            coupon.setEnd(end);
            couponDao.save(coupon);
            return true;
        }
    }

    @Override
    public Boolean remove(@NotNull String business, @NotNull String name, @NotNull Integer count) {
        CouponId id = CouponId.of(business, name);
        Optional<Coupon> optional = couponDao.findById(id);
        if (optional.isPresent()) {
            Coupon coupon = optional.get();
            if (coupon.getRemain() >= count) {
                coupon.setRemain(coupon.getRemain() - count);
                coupon.setTotal(coupon.getTotal() - count);
                return true;
            }
        }
        return false;
    }

    @Override
    public Coupon find(String business, String name) {
        CouponId id = CouponId.of(business, name);
        Optional<Coupon> optional = couponDao.findById(id);
        return optional.orElse(null);
    }

    @Override
    public List<Coupon> search(@NotNull String business, @NotNull String name, @NotNull Short type,
                               @NotNull BigDecimal minValue, @NotNull BigDecimal maxValue,
                               @NotNull BigDecimal minLimit, @NotNull BigDecimal maxLimit,
                               @NotNull Date start, @NotNull Date end,
                               @NotNull Integer page) {
        return couponDao.search(business, name, type, minValue, maxValue, minLimit, maxLimit, start, end, page * 10);
    }
}
