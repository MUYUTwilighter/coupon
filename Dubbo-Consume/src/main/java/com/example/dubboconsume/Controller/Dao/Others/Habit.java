package com.example.dubboconsume.Controller.Dao.Others;

public enum Habit {
    SALE(0.1),
    CHEAP(0.8),
    RELATIVE_CHEAP(0.9),
    SOME_DISCOUNT(0.95),
    NO_PREFERENCE(1.0);

    private final double habitDiscount;
    Habit(double discount) {
        this.habitDiscount = discount;
    }
    public double getHabitDiscount() {
        return habitDiscount;
    }

}
