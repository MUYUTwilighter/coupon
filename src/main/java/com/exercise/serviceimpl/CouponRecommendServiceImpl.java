package com.exercise.serviceimpl;


//import com.exercise.Dao.CouponDao;
import com.exercise.Dao.CouponRecommendDao;
import com.exercise.entity.Coupon;
//import com.exercise.mapper.CouponMapper;
import com.exercise.service.CouponRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponRecommendServiceImpl implements CouponRecommendService {

//    @Autowired
//    private CouponMapper couponMapper;

    @Autowired
    private CouponRecommendDao couponRecommendDao;
//
//    @Override
//    public void insert(Coupon coupon){
//        couponMapper.insert(coupon);
//    }
//
//    @Override
//    public List<Coupon> selectAll(){
//        List<Coupon> couponList=couponMapper.selectAll();
//        return couponList;
//    }

//    @Override
//    public List<Coupon> recommendCoupons(List<Coupon> coupons) {
//        Date today = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateString = formatter.format(today);
//        List<Coupon> filteredCoupons = coupons.stream()
//                .filter(coupon -> coupon.getEnd().compareTo(dateString) > 0)
//                .sorted(Comparator.comparing(Coupon::getLimitprice))
//                .collect(Collectors.toList());
//        return filteredCoupons;
//    }

    @Override
    public List<Coupon> recommendCoupons() {
        List<Coupon> filteredCoupons= couponRecommendDao.recommendCoupons();
        return filteredCoupons;
    }
}
