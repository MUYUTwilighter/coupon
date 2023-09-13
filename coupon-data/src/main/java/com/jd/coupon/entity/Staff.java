package com.jd.coupon.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author MUYU_Twilighter
 */
@Data
@Entity
@Table(schema = "staff")
public class Staff implements Serializable {
    public static final Staff EMPTY = new Staff();
    public static final String NAME_REGEX = "^[a-z0-9A-Z]+$";
    public static final String BUS_EMPTY = "EMPTY";
    public static final String BUS_ANY = "ANY";
    public static final Byte AUTH_COMMON = 0;
    public static final Byte AUTH_STAFF = 1;
    public static final Byte AUTH_BUS_ADMIN = 2;
    public static final Byte AUTH_SYS_ADMIN = 3;
    public static final Byte AUTH_ADMIN = 4;

    @Id
    @Column(length = 32)
    private String name = "default";
    @Column(length = 32)
    private String pwd;
    @Column
    private Byte auth;
    @Column(length = 32)
    private String business;

    public Boolean hasAuth(Byte auth) {
        Byte authThis = this.getAuth();
        if (authThis == null) {
            return false;
        } else {
            return auth == null || authThis >= auth;
        }
    }
}
