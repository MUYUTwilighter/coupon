package com.jd.coupon.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

/**
 * @author MUYU_Twilighter
 */
public interface RequestDto extends Serializable {
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
    Boolean getRejected();

    @Column(name = "approved")
    Long getApproval();

    void setId(Long id);

    void setCategory(Byte category);

    void setInitiate(Date initiate);

    void setInitiator(String initiator);

    void setRejected(Boolean rejected);

    void setApproval(Long approval);
}
