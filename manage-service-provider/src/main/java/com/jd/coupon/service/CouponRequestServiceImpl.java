package com.jd.coupon.service;

import com.jd.coupon.dao.CouponDao;
import com.jd.coupon.dao.CouponRequestDao;
import com.jd.coupon.entity.Coupon;
import com.jd.coupon.entity.CouponRequest;
import com.jd.coupon.entity.RequestDto;
import com.jd.coupon.entity.StaffRequest;
import com.jd.coupon.key.CouponId;
import com.jd.coupon.util.Executor;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * @author MUYU_Twilighter
 */
public class CouponRequestServiceImpl {
    private final Map<Byte, Predicate<CouponRequest>> executorMap = new HashMap<>();
    @Autowired
    private CouponDao couponDao;
    @Autowired
    private CouponRequestDao couponRequestDao;

    public CouponRequestServiceImpl() {
        executorMap.put(CouponRequest.CATE_CREATE, request -> {
            Coupon coupon = request.extractCoupon();
            if (couponDao.existsById(coupon.extractId())) {
                return false;
            }
            couponDao.save(coupon);
            return true;
        });
        executorMap.put(CouponRequest.CATE_DELETE, request -> {
            CouponId id = request.extractCoupon().extractId();
            couponDao.deleteById(id);
            return true;
        });
        executorMap.put(CouponRequest.CATE_POST, request -> {
            String business = request.getCouponBusiness();
            String name = request.getCouponName();
            Integer count = request.getCouponCount();
            couponDao.post(business, name, count);
            return true;
        });
        executorMap.put(CouponRequest.CATE_WITHDRAW, request -> {
            String business = request.getCouponBusiness();
            String name = request.getCouponName();
            Integer count = request.getCouponCount();
            couponDao.withdraw(business, name, count);
            return true;
        });
    }

    public Boolean approve(@NotNull Byte auth, @NotNull Long id) {
        CouponRequest request = couponRequestDao.find(id);
        if (request == null) {
            return false;
        }
        if (!request.nextApproval().equals(auth)) {
            return false;
        }
        request.rollApproval();
        if (request.nextApproval() == 0) {
            Predicate<CouponRequest> predicate = executorMap.get(request.getCategory());
            return predicate.test(request);
        }
        return true;
    }
}
