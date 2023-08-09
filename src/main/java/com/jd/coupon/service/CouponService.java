package com.jd.coupon.service;

import com.jd.coupon.entity.Coupon;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * @author MUYU_Twilighter <br/>
 * <p>
 * Auto extracted from {@link CouponServiceImpl} by Intellij IDEA
 */
@Service
public interface CouponService {
    Boolean exists(@NotNull String business, @NotNull String name);

    Boolean post(@NotNull String business, @NotNull String name, @NotNull Integer count);

    Boolean post(@NotNull String business, @NotNull String name,
                 @NotNull Short type, @NotNull Integer count,
                 @NotNull BigDecimal value, @NotNull BigDecimal limit,
                 @NotNull Date start, @NotNull Date end);

    Boolean remove(@NotNull String business, @NotNull String name, @Nullable Integer count);

    Boolean distribute(@NotNull String business, @NotNull String name, @NotNull Integer count);

    Coupon find(String business, String name);

    List<Coupon> search(@NotNull String business, @NotNull String name, @NotNull Short type,
                        @NotNull BigDecimal minValue, @NotNull BigDecimal maxValue,
                        @NotNull BigDecimal minLimit, @NotNull BigDecimal maxLimit,
                        @NotNull Date start, @NotNull Date end,
                        @NotNull Integer page);
}
