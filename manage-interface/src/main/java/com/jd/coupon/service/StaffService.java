package com.jd.coupon.service;

import com.jd.coupon.entity.StaffDto;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import java.util.List;

public interface StaffService {
    StaffDto info(@NotNull String name);

    List<StaffDto> search(@Nullable String name,
                          @Nullable String business,
                          @Nullable Short auth,
                          @Nullable Integer page);

    void register(@NotNull String initiator, @NotNull String name, @NotNull String pwd);

    void delete(@NotNull String initiator, @NotNull String name);

    void login(@NotNull String name, @NotNull String pwd);

    void changePwd(@NotNull String name, @NotNull String pwd, @NotNull String newPwd);

    Boolean changeBusiness(@NotNull String initiator, @NotNull String name, @NotNull String newBusiness);

    Boolean changeAuth(@NotNull String initiator, @NotNull String name, @NotNull Byte newAuth);
}
