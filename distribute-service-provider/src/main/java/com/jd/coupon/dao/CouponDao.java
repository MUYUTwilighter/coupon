package com.jd.coupon.dao;

import com.jd.coupon.entity.Coupon;
import com.jd.coupon.key.CouponId;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

/**
 * @author MUYU_Twilighter
 */
public interface CouponDao extends JpaRepository<Coupon, CouponId> {
    @Query(nativeQuery = true, value =
        "select c.business as business, c.name as name, c.end as end, c.limit_value as limitValue, " +
            "   c.start as start, c.type as type, c.value as value, " +
            "   c.remain - 1 as remain, c.total as total, c.usable_cate as usable_cate" +
            "from (select * from coupon " +
            "   where (?3 is null or end >= ?3) " +
            "       and (?4 is null or start <= ?4) " +
            "       and remain > 0) as c, " +
            "   (select * from interest " +
            "       where hobby = ?1 and i.job = ?2) as i " +
            "where c.business = i.coupon_business " +
            "   and c.name = i.coupon_name " +
            "order by i.relation DESC " +
            "limit ?5, ?6")
    List<Coupon> searchAndReduce(@NotNull String hobby, @NotNull String job,
                                 @Nullable Date start, @Nullable Date end,
                                 @NotNull Integer index, @NotNull Integer count);
}
