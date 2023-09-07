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
    @Column(name = "business", length = 32)
    String getBusiness();

    void setBusiness(String business);

    @Id
    @Column(name = "name", length = 32)
    String getName();

    void setName(String name);

    @Column(name = "type")
    Byte getType();

    void setType(Byte type);

    @Column(name = "value")
    BigDecimal getValue();

    void setValue(BigDecimal value);

    @Column(name = "limit_value")
    BigDecimal getLimitValue();

    void setLimitValue(BigDecimal limitValue);

    @Column(name = "start")
    Date getStart();

    void setStart(Date start);

    @Column(name = "end")
    Date getEnd();

    void setEnd(Date end);
}
