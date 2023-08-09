package com.jd.coupon.service;

import com.jd.coupon.entity.Staff;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MUYU_Twilighter
 */
@Service
public interface StaffService {
    Staff find(@NotNull String username);

    List<Staff> search(String name, String business, Short auth, @NotNull int count);

    Boolean modify(@NotNull String name, String business, Short auth, String pwd);

    Boolean verify(@NotNull String name, @NotNull String pwd);

    Boolean register(@NotNull String name, @NotNull String pwd);

    void destroy(@NotNull String name);

    Staff find(@NotNull String name, @NotNull Short auth);
}
