package com.example.dubboconsume.Controller.Dao.Others.Service;

import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;

@Service
public class Coupon implements Serializable {
//    总价
    private int total;
    //优惠金额
    private int discount;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private static final long serialVersionUID = 3608451818006447637L;
//优惠券类别
    private String business;
//优惠券名字
    private String name;
//开始时间
    private String start;
//截止时间
    private String end;
//推荐时间
    private String recommend;

    //    购买时间
    private String purchase;

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getPurchase() {
        return purchase;
    }

    public void setPurchase(String purchase) {
        this.purchase = purchase;
    }




    public Coupon() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}

