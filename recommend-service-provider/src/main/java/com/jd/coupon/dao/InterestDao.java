package com.jd.coupon.dao;

import com.jd.coupon.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InterestDao extends JpaRepository<Interest, Long> {
    @Query(nativeQuery = true, value =
        "delete from interest " +
            "where (?1 is null or hobby like ?1) " +
            "   and (?2 is null or job like ?2) " +
            "   and (?3 is null or couponName like ?3)" +
            "   and (?4 is null or couponBusiness like ?4)")
    void delete(String hobby, String job, String couponName, String couponBusiness);
}
