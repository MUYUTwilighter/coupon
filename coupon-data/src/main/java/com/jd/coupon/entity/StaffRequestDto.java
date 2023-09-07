package com.jd.coupon.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.sql.Date;

/**
 * @author MUYU_Twilighter
 */
public interface StaffRequestDto extends RequestDto {
    @Override
    @Id
    @Column(name = "id")
    Long getId();

    @Override
    @Column(name = "category")
    Byte getCategory();

    @Override
    @Column(name = "initiate")
    Date getInitiate();

    @Override
    @Column(name = "initiator")
    String getInitiator();

    @Override
    @Column(name = "rejected")
    Boolean getRejected();

    @Override
    @Column(name = "approved")
    Long getApproval();

    String getStaffName();

    void setStaffName(String staffName);

    String getStaffBusiness();

    void setStaffBusiness(String staffBusiness);

    Byte getStaffAuth();

    void setStaffAuth(Byte staffAuth);
}
