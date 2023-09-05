package com.jd.coupon.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author MUYU_Twilighter
 */
@Entity
@Table(schema = "staff")
public class Staff implements Serializable {
    public static final Staff EMPTY = new Staff();
    public static final String BUS_EMPTY = "EMPTY";
    public static final String BUS_ANY = "ANY";
    public static final Short AUTH_COMMON = 0;
    public static final Short AUTH_STAFF = 1;
    public static final Short AUTH_BUS_ADMIN = 2;
    public static final Short AUTH_SYS_ADMIN = 3;
    public static final Short AUTH_ADMIN = 4;

    @Id
    @Column(length = 32)
    private String name;
    @Column(length = 32)
    private String pwd;
    @Column
    private Short auth;
    @Column(length = 32)
    private String business;

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

    public Short getAuth() {
        return auth;
    }

    public void setAuth(Short auth) {
        this.auth = auth;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public Boolean beyond(Short auth) {
        if (this.auth == null) {
            return false;
        } else {
            return auth == null || this.auth >= auth;
        }
    }

    public Staff hide() {
        Staff staff = new Staff();
        staff.setName(name);
        staff.setBusiness(business);
        staff.setAuth(auth);
        return staff;
    }
}
