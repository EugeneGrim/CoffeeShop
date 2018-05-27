package com.coffeeshop.model;

import java.util.ResourceBundle;

public class Snack extends Goods {
    private final static double SNACK_MARGIN;

    static {
        ResourceBundle myResources = ResourceBundle.getBundle("com.coffeeshop.Margins");
        SNACK_MARGIN = Double.parseDouble(myResources.getString("SNACK_MARGIN"));
    }

    @Override
    public double getPrice() {
        return getCost() * SNACK_MARGIN;
    }
}
