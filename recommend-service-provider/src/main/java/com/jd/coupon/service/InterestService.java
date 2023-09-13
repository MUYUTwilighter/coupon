package com.jd.coupon.service;

import com.jd.coupon.dao.InterestDao;
import com.jd.coupon.entity.Interest;
import com.jd.coupon.key.CouponId;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author MUYU_Twilighter
 */
@DubboService
@Component
public class InterestService {
    @Autowired
    private InterestDao interestDao;

    public void set(CouponId couponId, String hobby, String job, Integer relation) {
        Interest interest = new Interest();
        interest.setHobby(hobby);
        interest.setJob(job);
        interest.setCouponBusiness(couponId.getBusiness());
        interest.setCouponName(couponId.getName());
        interest.setRelation(relation);
        interestDao.save(interest);
    }

    public void delete(CouponId couponId, String hobby, String job) {
        String couponBusiness = couponId == null ? null : couponId.getBusiness();
        String couponName = couponId == null ? null : couponId.getName();
        interestDao.delete(hobby, job, couponBusiness, couponName);
    }
}
