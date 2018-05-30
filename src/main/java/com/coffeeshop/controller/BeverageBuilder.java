package com.coffeeshop.controller;

import com.coffeeshop.model.Beverage;
import com.coffeeshop.model.BeverageDecorator;

import java.util.ArrayList;
import java.util.List;

public class BeverageBuilder {
    private Beverage beverage;
    private List<BeverageDecorator.Topping> toppings = new ArrayList<>();

    public BeverageBuilder(Beverage beverage){
        this.beverage = beverage;
    }

    public BeverageBuilder addTopping(BeverageDecorator.Topping topping) {
        toppings.add(topping);
        return this;
    }

    public BeverageBuilder removeTopping(BeverageDecorator.Topping topping) {
        toppings.remove(topping);
        return this;
    }

    public Beverage makeBeverage() {
        for (BeverageDecorator.Topping topping : toppings) {
            beverage = new BeverageDecorator(beverage, topping);
        }
        return beverage;
    }
}