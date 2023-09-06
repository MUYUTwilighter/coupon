package com.jd.coupon.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author MUYU_Twilighter
 */
@Entity
@Table(name = "coupon_request")
public class CouponRequest implements RequestDto {
    public static Byte CATE_CREATE = 0x00;
    public static Byte CATE_DELETE = 0x01;
    public static Byte CATE_POST = 0x02;
    public static Byte CATE_WITHDRAW = 0x03;

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
    @JoinTable(name = "staff")
    private String initiator;
    @Column(name = "rejected")
    private Boolean rejected;
    @Column(name = "approval")
    private Long approval;
    @Column(name = "coupon_business", length = 32)
    private String couponBusiness;
    @Column(name = "coupon_name", length = 32)
    private String couponName;
    @Column(name = "coupon_type")
    private Short couponType;
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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Byte getCategory() {
        return category;
    }

    @Override
    public Date getInitiate() {
        return initiate;
    }

    @Override
    public String getInitiator() {
        return initiator;
    }

    @Override
    public Boolean isRejected() {
        return rejected;
    }

    @Override
    public Long getApproval() {
        return approval;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setCategory(Byte category) {
        this.category = category;
    }

    @Override
    public void setInitiate(Date initiate) {
        this.initiate = initiate;
    }

    @Override
    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    @Override
    public void setRejected(Boolean rejected) {
        this.rejected = rejected;
    }

    @Override
    public void setApproval(Long approval) {
        this.approval = approval;
    }

    public String getCouponBusiness() {
        return this.couponBusiness;
    }

    public void setCouponBusiness(String couponBusiness) {
        this.couponBusiness = couponBusiness;
    }

    public String getCouponName() {
        return this.couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Short getCouponType() {
        return couponType;
    }

    public void setCouponType(Short couponType) {
        this.couponType = couponType;
    }

    public BigDecimal getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(BigDecimal couponValue) {
        this.couponValue = couponValue;
    }

    public BigDecimal getCouponLimitValue() {
        return couponLimitValue;
    }

    public void setCouponLimitValue(BigDecimal couponLimitValue) {
        this.couponLimitValue = couponLimitValue;
    }

    public Date getCouponStart() {
        return couponStart;
    }

    public void setCouponStart(Date couponStart) {
        this.couponStart = couponStart;
    }

    public Date getCouponEnd() {
        return couponEnd;
    }

    public void setCouponEnd(Date couponEnd) {
        this.couponEnd = couponEnd;
    }

    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
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
        return coupon;
    }
}
