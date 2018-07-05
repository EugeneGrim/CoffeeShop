package com.coffeeshop.model;

public class Beverage extends Goods {
    private final static double DRINK_MARGIN = Product.setMargin("DRINK_MARGIN");

    Beverage(String name, String description, double cost) {
        super(name, description, cost);
    }

    @Override
    public double getPrice() {
        return getCost() * DRINK_MARGIN;
    }
}
