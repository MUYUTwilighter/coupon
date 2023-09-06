package com.jd.coupon.dao;

import com.jd.coupon.entity.Coupon;
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
        value = "select business, name, end, limit_value as limitValue, start, type, value from coupon " +
            "where (?1 is null or business like ?1) " +
            "   and (?2 is null or name like ?2) " +
            "   and (?3 is null or type = ?3) " +
            "   and (?4 is null or value >= ?4) " +
            "   and (?5 is null or value <= ?5) " +
            "   and (?6 is null or limit_value >= ?6) " +
            "   and (?7 is null or limit_value <= ?7) " +
            "   and (?8 is null or end >= ?8) " +
            "   and (?9 is null or start <= ?9) " +
            "   and remain > 0 " +
            "limit ?10, 10")
    List<Map<String, Object>> searchAvailable(@Nullable String business, @Nullable String name, @Nullable Short type,
                              @Nullable BigDecimal minValue, @Nullable BigDecimal maxValue,
                              @Nullable BigDecimal minLimit, @Nullable BigDecimal maxLimit,
                              @Nullable Date start, @Nullable Date end,
                              @NotNull Integer index);

    @Query(nativeQuery = true,
        value = "select c.business as business, c.name as name, c.end as end, c.limit_value as limitValue, " +
            "   c.start as start, c.type as type, c.value as value " +
            "from (select * from coupon " +
            "   where (?3 is null or end >= ?3) " +
            "       and (?4 is null or start <= ?4) " +
            "       and remain > 0) as c, " +
            "   (select * from interest " +
            "       where hobby = ?1 and i.job = ?2) as i " +
            "where c.business = i.coupon_business " +
            "   and c.name = i.coupon_name " +
            "order by i.relation DESC " +
            "limit ?5, 10")
    List<Map<String, Object>> searchInterest(@NotNull String hobby, @NotNull String job,
                                   @Nullable Date start, @Nullable Date end,
                                   @NotNull Integer index);
}
