package com.jd.coupon.service;

import com.jd.coupon.dao.CouponDao;
import com.jd.coupon.dao.CouponRequestDao;
import com.jd.coupon.entity.Coupon;
import com.jd.coupon.entity.CouponRequest;
import com.jd.coupon.key.CouponId;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * @author MUYU_Twilighter
 */
@DubboService
@Component
public class CouponRequestServiceImpl implements CouponRequestService {
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

    @Override
    public CouponRequest find(Long id) {
        return couponRequestDao.find(id);
    }

    @Override
    public Boolean approve(@NotNull Long id, @NotNull Byte auth) {
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

    @Override
    public void withdraw(@NotNull Long id) {
        this.couponRequestDao.deleteById(id);
    }

    @Override
    public List<CouponRequest> search(@Nullable Byte category,
                                      @Nullable Date start,
                                      @Nullable Date end,
                                      @Nullable String initiator,
                                      @Nullable Boolean rejected,
                                      @Nullable Byte nextApprove,
                                      @Nullable Integer page) {
        page = page == null || page < 0 ? 0 : page;
        return this.couponRequestDao.search(category, start, end, initiator, rejected, nextApprove, page * 10);
    }
}
