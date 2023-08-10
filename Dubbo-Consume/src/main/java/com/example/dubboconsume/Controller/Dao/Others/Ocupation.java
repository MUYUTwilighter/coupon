package com.example.dubboconsume.Controller.Dao.Others;

public enum Ocupation {
    Student(1, 200),
    Doctor(1, 1000),
    Teacher(1, 1500),
    Police(1, 1000),
    Engineer(1, 10000),
    Driver(1, 5000),
    Nurse(1, 5000),
    Boss(1, 20000),
    Cleaner(1, 3000),
    Worker(1, 10000);
    private final int minPrice;
    private final int maxPrice;

    Ocupation(int minPrice, int maxPrice) {
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

