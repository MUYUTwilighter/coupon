package com.jd.coupon.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author MUYU_Twilighter
 */
public interface StaffDto extends Serializable {
    @Id
    @Column(name = "name", length = 32)
    String getName();

    @Column(name = "auth")
    Byte getAuth();

    @Column(name = "business")
    String getBusiness();

    default Boolean beyond(Byte auth) {
        Byte authThis = this.getAuth();
        if (authThis == null) {
            return false;
        } else {
            return auth == null || authThis >= auth;
        }
    }
}
