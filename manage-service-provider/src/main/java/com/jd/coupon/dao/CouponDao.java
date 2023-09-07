package com.jd.coupon.dao;

import com.jd.coupon.entity.Coupon;
import com.jd.coupon.entity.CouponDto;
import com.jd.coupon.key.CouponId;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * @author MUYU_Twilighter
 */
public interface CouponDao extends JpaRepository<Coupon, CouponId> {
    @Query(nativeQuery = true,
        value = "select * from coupon " +
            "where business like ?1 and name like ?2" +
            "   and (?3 is null or type = ?3)" +
            "   and (?4 is null or value >= ?4) " +
            "   and (?5 is null or value <= ?5)" +
            "   and (?6 is null or limit_value >= ?6) " +
            "   and (?7 is null or limit_value <= ?7) " +
            "   and (?8 is null or remain >= ?8) " +
            "   and (?9 is null or total >= ?9) " +
            "   and (?10 is null or end >= ?10) " +
            "   and (?11 is null or start <= ?11) " +
            "limit ?12, 10")
    List<Coupon> search(@Nullable String business, @Nullable String name, @Nullable Byte type,
                        @Nullable BigDecimal minValue, @Nullable BigDecimal maxValue,
                        @Nullable BigDecimal minLimit, @Nullable BigDecimal maxLimit,
                        @Nullable Integer remain, @Nullable Integer total,
                        @Nullable Date start, @Nullable Date end,
                        @NotNull Integer index);

    @Query(nativeQuery = true,
        value = "update coupon " +
            "set remain = remain + ?3, total = total + ?3 " +
            "where business = ?1 and name = ?2")
    void post(@NotNull String business, @NotNull String name, @NotNull Integer count);

    @Query(nativeQuery = true,
        value = "update coupon " +
            "set remain = remain - ?3, total = total - ?3 " +
            "where business = ?1 and name = ?2" +
            "   and remain >= count")
    void withdraw(@NotNull String business, @NotNull String name, @NotNull Integer count);
}
