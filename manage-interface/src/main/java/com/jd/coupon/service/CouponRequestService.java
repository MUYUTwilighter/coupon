package com.jd.coupon.service;

import com.jd.coupon.entity.CouponRequest;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import java.sql.Date;
import java.util.List;

public interface CouponRequestService {
    CouponRequest find(Long id);

    Boolean approve(@NotNull Long id, @NotNull Byte auth);

    void withdraw(@NotNull Long id);

    List<CouponRequest> search(@Nullable Byte category,
                               @Nullable Date start,
                               @Nullable Date end,
                               @Nullable String initiator,
                               @Nullable Boolean rejected,
                               @Nullable Byte nextApprove,
                               @Nullable Integer page);
}
