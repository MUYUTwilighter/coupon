package com.jd.coupon.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author MUYU_Twilighter
 */
@Entity
@Table(
    name = "interest",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"hobby", "job", "coupon_business", "coupon_name"}
    )
)
public class Interest implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "hobby", length = 32)
    private String hobby;
    @Column(name = "job", length = 32)
    private String job;
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "coupon_business", referencedColumnName = "business"),
        @JoinColumn(name = "coupon_name", referencedColumnName = "name")
    })
    private Coupon coupon;
    @Column(name = "relation")
    private Integer relation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCouponBusiness() {
        return coupon.getBusiness();
    }

    public void setCouponBusiness(String couponBusiness) {
        coupon.setBusiness(couponBusiness);
    }

    public String getCouponName() {
        return coupon.getName();
    }

    public void setCouponName(String couponName) {
        coupon.setName(couponName);
    }

    public Integer getRelation() {
        return relation;
    }

    public void setRelation(Integer relation) {
        this.relation = relation;
    }
}
