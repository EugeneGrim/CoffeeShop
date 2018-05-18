package com.coffeeshop;

import java.util.ArrayList;
import java.util.List;

class BeverageBuilder {
    private Beverage beverage;
    private List<Toppings> toppings = new ArrayList<>();

    BeverageBuilder(Beverage beverage){
        this.beverage = beverage;
    }

    BeverageBuilder addTopping(Toppings topping) {
        toppings.add(topping);
        return this;
    }

    BeverageBuilder removeTopping(Toppings topping) {
        toppings.remove(topping);
        return this;
    }

    Beverage makeBeverage() {
        return Beverage.makeBeverage(beverage, toppings);
    }
}