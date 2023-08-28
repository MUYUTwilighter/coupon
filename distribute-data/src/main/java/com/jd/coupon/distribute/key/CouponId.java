package com.jd.coupon.distribute.key;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @author MUYU_Twilighter
 */
public class CouponId implements Serializable {
    @Autowired
    private String business;
    @Autowired
    private String name;

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static CouponId of(@NotNull String business, @NotNull String name) {
        CouponId id = new CouponId();
        id.business = business;
        id.name = name;
        return id;
    }
}