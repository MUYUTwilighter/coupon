package com.jd.coupon.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author MUYU_Twilighter
 */
public interface CouponDto extends Serializable {
    @Id
    @Column(length = 32)
    String getBusiness();

    void setBusiness(String business);

    @Id
    @Column(length = 32)
    String getName();

    void setName(String name);

    @Column
    Short getType();

    void setType(Short type);

    @Column
    BigDecimal getValue();

    void setValue(BigDecimal value);

    @Column
    BigDecimal getLimitValue();

    void setLimitValue(BigDecimal limitValue);

    @Column
    Date getStart();

    void setStart(Date start);

    @Column
    Date getEnd();

    void setEnd(Date end);
}
