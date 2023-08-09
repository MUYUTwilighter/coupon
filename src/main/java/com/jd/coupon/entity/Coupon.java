package com.jd.coupon.entity;

import com.jd.coupon.key.CouponId;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author MUYU_Twilighter
 */
@Entity
@Table(schema = "coupon")
@IdClass(CouponId.class)
public class Coupon implements Serializable {
    @Id
    @Column(length = 32)
    private String business;
    @Id
    @Column(length = 32)
    private String name;
    @Column
    private Short type;
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

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getLimitValue() {
        return limitValue;
    }

    public void setLimitValue(BigDecimal limitValue) {
        this.limitValue = limitValue;
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
