package com.coffeeshop;

enum Toppings {
    MILK,
    CHOCOLATE,
    WHIPPED_MILK
}

abstract class BeverageDecorator extends Beverage {
    private Beverage beverage;

    BeverageDecorator(Beverage beverage) {
        this.beverage = beverage;
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
