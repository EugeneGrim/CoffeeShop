package com.coffeeshop.model;

public class Snack extends Goods {
    private final static double SNACK_MARGIN = Product.setMargin("SNACK_MARGIN");

    Snack(String name, String description, double cost) {
        super(name, description, cost);
    }

    @Override
    public double getPrice() {
        return getCost() * SNACK_MARGIN;
    }
}
