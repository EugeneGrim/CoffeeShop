package com.coffeeshop.model;

import java.util.ResourceBundle;

public class HotFood extends Goods {
    private final static double FOOD_MARGIN;

    static {
        ResourceBundle myResources = ResourceBundle.getBundle("com.coffeeshop.Margins");
        FOOD_MARGIN = Double.parseDouble(myResources.getString("FOOD_MARGIN"));
    }

    @Override
    public double getPrice() {
        return getCost() * FOOD_MARGIN;
    }
}
