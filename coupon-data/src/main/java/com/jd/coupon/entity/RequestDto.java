package com.jd.coupon.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Date;

/**
 * @author MUYU_Twilighter
 */
public interface RequestDto {
    Long APPR_TWICE = (Staff.AUTH_ADMIN.longValue() << 8) | Staff.AUTH_SYS_ADMIN;
    Long APPR_ONCE = Staff.AUTH_ADMIN.longValue();

    @Id
    @Column(name = "id")
    Long getId();

    @Column(name = "category")
    Byte getCategory();

    @Column(name = "initiate")
    Date getInitiate();

    @Column(name = "initiator")
    String getInitiator();

    @Column(name = "rejected")
    Boolean isRejected();

    @Column(name = "approved")
    Long getApproval();

    void setId(Long id);

    void setCategory(Byte category);

    void setInitiate(Date initiate);

    void setInitiator(String initiator);

    void setRejected(Boolean rejected);

    void setApproval(Long approval);

    default Byte nextApproval() {
        return (byte) (this.getApproval() & 0xFF);
    }

    default Boolean rollApproval(Byte auth) {
        if (this.nextApproval().equals(auth)) {
            Long approval = this.getApproval();
            approval >>>= 8;
            setApproval(approval);
            return true;
        } else {
            return false;
        }
    }

    default Boolean hasApproved() {
        return this.getApproval() == 0;
    }
}
