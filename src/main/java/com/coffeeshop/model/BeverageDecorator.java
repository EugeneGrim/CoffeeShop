package com.coffeeshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class BeverageDecorator extends Beverage {
    private final static double TOPPING_MARGIN = Goods.setMargin("TOPPING_MARGIN");

    @Setter
    @Getter
    @AllArgsConstructor
    public static class Topping {
        private String name;
        private String description;
        private double cost;

    }
    @Setter
    private Beverage beverage;

    public BeverageDecorator(Beverage beverage, Topping topping) {
        this.beverage = beverage;
        this.setName(topping.getName());
        this.setDescription(topping.getDescription());
        this.setCost(topping.getCost());
    }

    @Override
    public double getPrice() {
        return beverage.getPrice() + this.getCost() * TOPPING_MARGIN;
    }

    @Override
    public String toString() {
        return beverage + " + " + this.getName();
    }
}
