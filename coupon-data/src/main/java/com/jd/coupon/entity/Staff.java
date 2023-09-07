package com.jd.coupon.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author MUYU_Twilighter
 */
@Entity
@Table(schema = "staff")
public class Staff implements StaffDto {
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

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public Byte getAuth() {
        return auth;
    }

    public void setAuth(Byte auth) {
        this.auth = auth;
    }


    @Override
    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }
}
