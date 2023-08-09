package com.jd.coupon.dao;

import com.jd.coupon.entity.Coupon;
import com.jd.coupon.key.CouponId;
import com.sun.istack.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * @author MUYU_Twilighter
 */
public interface CouponDao extends JpaRepository<Coupon, CouponId> {
    @Query(nativeQuery = true,
        value = "select * from coupon " +
            "where business like ?1 and name like ?2" +
            "   and (?3 is null or type = ?3)" +
            "   and value >= ?4 and value <= ?5" +
            "   and limit_value >= ?6 and limit_value <= ?7" +
            "   and end >= ?8 and start <= ?9 " +
            "limit ?10, 10")
    List<Coupon> search(@NotNull String business, @NotNull String name, @NotNull Short type,
                        @NotNull BigDecimal minValue, @NotNull BigDecimal maxValue,
                        @NotNull BigDecimal minLimit, @NotNull BigDecimal maxLimit,
                        @NotNull Date start, @NotNull Date end,
                        @NotNull Integer index);
}
