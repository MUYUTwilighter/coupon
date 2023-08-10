package com.example.dubboconsume.Controller.Dao.Others;

import com.example.dubboconsume.Controller.Dao.Others.PriceRange;

public  enum Hobby {
    GAME(100, 500),
    MUSIC(500, 10000),
    ART(10, 300),
    SPORTS(50, 1000),
    TRAVEL(500, 20000),
    TV_MOVIE(19, 100),
    FOOD(10, 2000),
    BOOK(10, 200);

    private final int minPrice;
    private final int maxPrice;
    Hobby(int minPrice, int maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }
    public int getMinPrice() {
        return minPrice;
    }
    public int getMaxPrice() {
        return maxPrice;
    }
    public PriceRange getPriceRange(){
        return new PriceRange(minPrice,maxPrice);
    }
}