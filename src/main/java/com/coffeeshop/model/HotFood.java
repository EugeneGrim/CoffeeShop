package com.coffeeshop.model;

public class HotFood extends Goods {
    private final static double FOOD_MARGIN = Product.setMargin("FOOD_MARGIN");

    HotFood(String name, String description, double cost) {
        super(name, description, cost);
    }

    @Override
    public double getPrice() {
        return getCost() * FOOD_MARGIN;
    }
}
