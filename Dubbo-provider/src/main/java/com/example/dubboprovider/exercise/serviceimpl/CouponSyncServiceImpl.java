package com.example.dubboprovider.exercise.serviceimpl;

import com.example.dubboprovider.exercise.Dao.CouponRecommendDao;
import com.example.dubboprovider.exercise.Dao.CouponSyncDao;
import com.example.dubboprovider.exercise.entity.Coupon;
import com.example.dubboprovider.exercise.entity.RecommendCoupon;
import com.example.dubboprovider.exercise.service.CouponSyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponSyncServiceImpl implements CouponSyncService {
    @Autowired
    private CouponSyncDao couponSyncDao;

    @Autowired
    private CouponRecommendDao couponRecommendDao;

    @Override
    @Scheduled(fixedRate = 7 * 24 * 60 * 60 * 1000) // 每隔7天执行一次
    public void syncAndSaveCoupons() {
        List<Coupon> coupons = couponRecommendDao.recommendCoupons();
        for (Coupon coupon : coupons) {
            RecommendCoupon existingCoupon = couponSyncDao.findById(coupon.getId());
            if (existingCoupon != null) {
                // 如果存在相同的优惠券，则进行更新操作
                existingCoupon.setName(coupon.getName());
                existingCoupon.setDiscount(coupon.getDiscount());
                existingCoupon.setTotal(coupon.getTotal());
                existingCoupon.setStart(coupon.getStart());
                existingCoupon.setEnd(coupon.getEnd());
                existingCoupon.setRecommend(coupon.getRecommend());
                existingCoupon.setPurchase(coupon.getPurchase());
                couponSyncDao.save(existingCoupon);
            } else {
                // 如果不存在相同的优惠券，则进行新增操作
                RecommendCoupon newCoupon = new RecommendCoupon();
                newCoupon.setId(coupon.getId());
                newCoupon.setBusiness(coupon.getBusiness());
                newCoupon.setName(coupon.getName());
                newCoupon.setDiscount(coupon.getDiscount());
                newCoupon.setTotal(coupon.getTotal());
                newCoupon.setStart(coupon.getStart());
                newCoupon.setEnd(coupon.getEnd());
                newCoupon.setRecommend(coupon.getRecommend());
                newCoupon.setPurchase(coupon.getPurchase());
                couponSyncDao.save(newCoupon);
            }
        }
    }
}
