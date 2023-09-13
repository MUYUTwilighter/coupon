package com.jd.coupon.entity;

import com.jd.coupon.key.CouponId;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author MUYU_Twilighter
 */
@Data
public class CouponDto implements Serializable {
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

    public static CouponDto of(Coupon coupon) {
        CouponDto couponDto = new CouponDto();
        couponDto.setBusiness(coupon.getBusiness());
        couponDto.setName(coupon.getName());
        couponDto.setType(coupon.getType());
        couponDto.setValue(coupon.getValue());
        couponDto.setLimitValue(coupon.getLimitValue());
        couponDto.setRemain(coupon.getRemain());
        couponDto.setTotal(coupon.getTotal());
        couponDto.setStart(coupon.getStart());
        couponDto.setEnd(coupon.getEnd());
        couponDto.setUsableCate(couponDto.getUsableCate());
        return couponDto;
    }

    public static List<CouponDto> of(Iterable<Coupon> coupons) {
        List<CouponDto> couponDtos = new LinkedList<>();
        for (Coupon coupon : coupons) {
            CouponDto couponDto = CouponDto.of(coupon);
            couponDtos.add(couponDto);
        }
        return couponDtos;
    }
}
