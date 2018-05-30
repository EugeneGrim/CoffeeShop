package com.coffeeshop.model;

public class Snack extends Goods {
    private final static double SNACK_MARGIN = Goods.setMargin("SNACK_MARGIN");

    @Override
    public double getPrice() {
        return getCost() * SNACK_MARGIN;
    }
}
