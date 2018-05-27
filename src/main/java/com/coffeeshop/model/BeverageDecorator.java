package com.coffeeshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ResourceBundle;

public class BeverageDecorator extends Beverage {

    @Setter
    @Getter
    @AllArgsConstructor
    static class Topping {
        private String name;
        private String description;
        private double cost;
    }

    @Setter
    private Beverage beverage;
    private final static double TOPPING_MARGIN;

    static {
        ResourceBundle myResources = ResourceBundle.getBundle("com.coffeeshop.Margins");
        TOPPING_MARGIN = Double.parseDouble(myResources.getString("TOPPING_MARGIN"));
    }

    @Deprecated
    BeverageDecorator() {
        this.setName("");
        this.setDescription("");
    }

    @Deprecated
    BeverageDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    BeverageDecorator(Beverage beverage, Topping topping) {
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

class Milk extends BeverageDecorator {
    Milk(Beverage beverage) {
        super(beverage);
        this.setName("Milk");
        this.setCost(10.0);
    }
}

class WhippedMilk extends BeverageDecorator {
    WhippedMilk(Beverage beverage) {
        super(beverage);
        this.setName("Whipped Milk");
        this.setCost(10.0);
    }
}

class Chocolate extends BeverageDecorator {
    Chocolate(Beverage beverage) {
        super(beverage);
        this.setName("Chocolate");
        this.setCost(8.0);
    }
}
