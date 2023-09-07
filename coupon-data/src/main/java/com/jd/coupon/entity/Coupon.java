package com.jd.coupon.entity;

import com.jd.coupon.key.CouponId;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author MUYU_Twilighter
 */
@Entity
@Table(name = "coupon")
@IdClass(CouponId.class)
public class Coupon implements CouponDto {
    @Id
    @Column(length = 32)
    private String business;
    @Id
    @Column(length = 32)
    private String name;
    @Column
    private Byte type;
    @Column
    private BigDecimal value;
    @Column
    private BigDecimal limitValue;
    @Column
    private Integer remain;
    @Column
    private Integer total;
    @Column
    private Date start;
    @Column
    private Date end;

    @Override
    public String getBusiness() {
        return business;
    }

    @Override
    public void setBusiness(String business) {
        this.business = business;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Byte getType() {
        return type;
    }

    @Override
    public void setType(Byte type) {
        this.type = type;
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public BigDecimal getLimitValue() {
        return limitValue;
    }

    @Override
    public void setLimitValue(BigDecimal limitValue) {
        this.limitValue = limitValue;
    }

    @Override
    public Date getStart() {
        return start;
    }

    @Override
    public void setStart(Date start) {
        this.start = start;
    }

    @Override
    public Date getEnd() {
        return end;
    }

    @Override
    public void setEnd(Date end) {
        this.end = end;
    }

    public Integer getRemain() {
        return remain;
    }

    public void setRemain(Integer remain) {
        this.remain = remain;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public CouponId extractId() {
        return CouponId.of(business, name);
    }
}