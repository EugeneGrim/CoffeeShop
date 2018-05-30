package com.coffeeshop.model;

public class HotFood extends Goods {
    private final static double FOOD_MARGIN = Goods.setMargin("FOOD_MARGIN");

    @Override
    public double getPrice() {
        return getCost() * FOOD_MARGIN;
    }
}
