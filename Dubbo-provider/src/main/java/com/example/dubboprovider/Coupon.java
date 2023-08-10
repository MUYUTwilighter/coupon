package com.example.dubboprovider;

import java.io.Serializable;

public class Coupon implements Serializable {

    private static final long serialVersionUID = 3608451818006447637L;
    private int total;
    private int discount;
    public Coupon(int total, int discount) {
        this.total = total;
        this.discount = discount;
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
