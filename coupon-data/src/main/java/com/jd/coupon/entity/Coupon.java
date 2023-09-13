package com.jd.coupon.entity;

import com.jd.coupon.key.CouponId;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author MUYU_Twilighter
 */
@Data
@Entity
@Table(name = "coupon")
@IdClass(CouponId.class)
public class Coupon implements Serializable {
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

    public Boolean canUseOn(Long productCate) {
        return (this.getUsableCate() & productCate) != 0;
    }

    public CouponId extractId() {
        return CouponId.of(this.getBusiness(), this.getName());
    }
}