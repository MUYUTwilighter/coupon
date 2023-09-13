package com.jd.coupon.entity;

import com.jd.coupon.util.RequestUtil;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

/**
 * @author MUYU_Twilighter
 */
@Data
@Entity
@Table(name = "coupon_request")
public class CouponRequest {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;
    @Column(name = "category")
    private Byte category;
    @Column(name = "initiate")
    private Date initiate;
    @ManyToOne
    @JoinColumn(name = "initiator", referencedColumnName = "name")
    private Staff initiator;
    @Column(name = "rejected")
    private Boolean rejected;
    @Column(name = "approval")
    private Long approval;
    @Column(name = "coupon_business", length = 32)
    private String couponBusiness;
    @Column(name = "coupon_name", length = 32)
    private String couponName;
    @Column(name = "coupon_type")
    private Byte couponType;
    @Column(name = "coupon_value")
    private BigDecimal couponValue;
    @Column(name = "coupon_limit_value")
    private BigDecimal couponLimitValue;
    @Column(name = "coupon_start")
    private Date couponStart;
    @Column(name = "coupon_end")
    private Date couponEnd;
    @Column(name = "coupon_count")
    private Integer couponCount;
    @Column(name = "coupon_usable_cate")
    private Long couponUsableCate;

    public String getInitiator() {
        return this.initiator.getName();
    }

    public void setInitiator(String initiator) {
        this.initiator.setName(initiator);
    }

    public Coupon extractCoupon() {
        Coupon coupon = new Coupon();
        coupon.setBusiness(getCouponBusiness());
        coupon.setName(getCouponName());
        coupon.setType(getCouponType());
        coupon.setValue(getCouponValue());
        coupon.setLimitValue(getCouponLimitValue());
        coupon.setRemain(getCouponCount());
        coupon.setTotal(getCouponCount());
        coupon.setStart(getCouponStart());
        coupon.setEnd(getCouponEnd());
        coupon.setUsableCate(getCouponUsableCate());
        return coupon;
    }

    public static CouponRequest initCreate(@NotNull String initiator,
                                           @NotNull String couponBusiness,
                                           @NotNull String couponName,
                                           @NotNull Byte type,
                                           @NotNull BigDecimal couponValue,
                                           @NotNull BigDecimal couponLimitValue,
                                           @NotNull Integer couponCount,
                                           @NotNull Date couponStart,
                                           @NotNull Date couponEnd,
                                           @NotNull Long couponUsableCate) {
        CouponRequest request = new CouponRequest();
        request.setCategory(RequestUtil.CATE_COUPON_CREATE);
        request.setInitiate(Date.valueOf(LocalDate.now()));
        request.setInitiator(initiator);
        request.setRejected(false);
        request.setApproval(RequestUtil.APPR_TWICE);
        request.setCouponBusiness(couponBusiness);
        request.setCouponName(couponName);
        request.setCouponType(type);
        request.setCouponValue(couponValue);
        request.setCouponLimitValue(couponLimitValue);
        request.setCouponCount(couponCount);
        request.setCouponStart(couponStart);
        request.setCouponEnd(couponEnd);
        request.setCouponUsableCate(couponUsableCate);
        return request;
    }

    public static CouponRequest initDelete(@NotNull String initiator,
                                           @NotNull String couponBusiness,
                                           @NotNull String couponName) {
        CouponRequest request = new CouponRequest();
        request.setCategory(RequestUtil.CATE_STAFF_DELETE);
        request.setInitiate(Date.valueOf(LocalDate.now()));
        request.setInitiator(initiator);
        request.setRejected(false);
        request.setApproval(RequestUtil.APPR_TWICE);
        request.setCouponBusiness(couponBusiness);
        request.setCouponName(couponName);
        return request;
    }

    public static CouponRequest initPost(@NotNull String initiator,
                                         @NotNull String couponBusiness,
                                         @NotNull String couponName,
                                         @NotNull Integer couponCount) {
        CouponRequest request = new CouponRequest();
        request.setCategory(RequestUtil.CATE_COUPON_POST);
        request.setInitiate(Date.valueOf(LocalDate.now()));
        request.setInitiator(initiator);
        request.setRejected(false);
        request.setApproval(RequestUtil.APPR_TWICE);
        request.setCouponBusiness(couponBusiness);
        request.setCouponName(couponName);
        request.setCouponCount(couponCount);
        return request;
    }

    public static CouponRequest initWithdraw(@NotNull String initiator,
                                             @NotNull String couponBusiness,
                                             @NotNull String couponName,
                                             @NotNull Integer couponCount) {
        CouponRequest request = new CouponRequest();
        request.setCategory(RequestUtil.CATE_COUPON_WITHDRAW);
        request.setInitiate(Date.valueOf(LocalDate.now()));
        request.setInitiator(initiator);
        request.setRejected(false);
        request.setApproval(RequestUtil.APPR_TWICE);
        request.setCouponBusiness(couponBusiness);
        request.setCouponName(couponName);
        request.setCouponCount(couponCount);
        return request;
    }

    public Byte nextApproval() {
        return (byte) (this.getApproval() & 0xFF);
    }

    public void rollApproval() {
        Long approval = this.getApproval();
        approval >>>= 8;
        setApproval(approval);
    }

    public Boolean hasApproved() {
        return this.getApproval() == 0;
    }
}
