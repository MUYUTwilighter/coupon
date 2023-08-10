package com.example.dubboconsume.Controller.Dao.Others.Service;

import com.alibaba.dubbo.config.annotation.Service;

import java.io.Serializable;

@Service
public class Coupon implements Serializable {
    private int total;
    private int discount;
    private static final long serialVersionUID = 3608451818006447637L;

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

