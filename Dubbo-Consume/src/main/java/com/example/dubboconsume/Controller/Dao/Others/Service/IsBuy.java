package com.example.dubboconsume.Controller.Dao.Others.Service;

import com.alibaba.dubbo.config.annotation.Service;

@Service
public class IsBuy {
    private boolean isBuy;
    private int price;
    private int discount;
    private String recommendDate;
    private String buyDate;

    public IsBuy(boolean isBuy, int price, int discount, String recommendDate, String buyDate) {
        this.isBuy = isBuy;
        this.price = price;
        this.discount = discount;
        this.recommendDate = recommendDate;
        this.buyDate = buyDate;
    }

    public IsBuy() {
    }

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getRecommendDate() {
        return recommendDate;
    }

    public void setRecommendDate(String recommendDate) {
        this.recommendDate = recommendDate;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    @Override
    public String toString() {
        return "IsBuy{" +
                "isBuy=" + isBuy +
                ", price=" + price +
                ", discount=" + discount +
                ", recommendDate='" + recommendDate + '\'' +
                ", buyDate='" + buyDate + '\'' +
                '}';
    }
}
