package com.coffeeshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class BeverageDecorator extends Beverage {
    private final static double TOPPING_MARGIN = Product.setMargin("TOPPING_MARGIN");

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
        super(topping.getName(), topping.getDescription(), topping.getCost());
        this.beverage = beverage;
    }

    @Override
    public double getPrice() {
        return beverage.getPrice() + this.getCost() * TOPPING_MARGIN;
    }

    @Override
    public String toString() {
        String result = beverage.toString();

        if (result.contains(this.getName())) {
            int index = result.indexOf("x " + this.getName());
            if (index > 1) {
                Integer count = Integer.parseInt(result.substring(index - 1, index));
                result = result.replace(count + "x " + this.getName(),
                        (++count).toString() + "x " + this.getName());
            } else {
                result = result.replace(this.getName(), "2x " + this.getName());
            }
            return result;
        } else {
            return beverage + " + " + this.getName();
        }
    }
}
