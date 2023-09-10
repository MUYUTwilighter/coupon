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
    @Column(name = "business", length = 32)
    private String business;
    @Id
    @Column(name = "name", length = 32)
    private String name;
    @Column(name = "type")
    private Byte type;
    @Column(name = "value")
    private BigDecimal value;
    @Column(name = "limit_value")
    private BigDecimal limitValue;
    @Column(name = "remain")
    private Integer remain;
    @Column(name = "total")
    private Integer total;
    @Column(name = "start")
    private Date start;
    @Column(name = "end")
    private Date end;
    @Column(name = "usable_cate")
    private Long usableCate;

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

    public Long getUsableCate() {
        return usableCate;
    }

    public void setUsableCate(Long usableCate) {
        this.usableCate = usableCate;
    }
}