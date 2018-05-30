package com.coffeeshop.model;

public class Beverage extends Goods {
    private final static double DRINK_MARGIN = Goods.setMargin("DRINK_MARGIN");

    @Override
    public double getPrice() {
        return getCost() * DRINK_MARGIN;
    }
}

