package com.jd.coupon.service;

import com.jd.coupon.entity.Coupon;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public interface CouponService {
    List<Coupon> search(@Nullable String business,
                        @Nullable String name,
                        @Nullable Byte type,
                        @Nullable BigDecimal minValue,
                        @Nullable BigDecimal maxValue,
                        @Nullable BigDecimal minLimit,
                        @Nullable BigDecimal maxLimit,
                        @Nullable Integer remain,
                        @Nullable Integer total,
                        @Nullable Date start,
                        @Nullable Date end,
                        @Nullable Long usableCate,
                        @Nullable Integer page);

    void create(@NotNull String initiator,
                @NotNull String business,
                @NotNull String name,
                @NotNull Byte type,
                @NotNull BigDecimal value,
                @NotNull BigDecimal limitValue,
                @NotNull Integer count,
                @NotNull Date start,
                @NotNull Date end,
                @NotNull Long usableCate);

    void delete(@NotNull String initiator,
                @NotNull String business,
                @NotNull String name);

    void post(@NotNull String initiator,
              @NotNull String business,
              @NotNull String name,
              @NotNull Integer count);

    void withdraw(@NotNull String initiator,
                  @NotNull String business,
                  @NotNull String name,
                  @NotNull Integer count);
}
