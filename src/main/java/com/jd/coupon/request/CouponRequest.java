package com.jd.coupon.request;

import com.jd.coupon.entity.Coupon;
import springfox.documentation.annotations.ApiIgnore;

import java.io.Serializable;

/**
 * @author MUYU_Twilighter
 */
@ApiIgnore
public class CouponRequest implements Serializable {
    public static final Short POST = 1;
    public static final Short REMOVE = 2;

    private String id;
    private Short category;
    private String initiator;
    private Coupon coupon;
    private Short approve;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Short getCategory() {
        return category;
    }

    public void setCategory(Short category) {
        this.category = category;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public Short getApprove() {
        return approve;
    }

    public void setApprove(Short approve) {
        this.approve = approve;
    }
}
