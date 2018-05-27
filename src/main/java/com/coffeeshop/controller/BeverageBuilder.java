package com.coffeeshop.controller;

import com.coffeeshop.model.Beverage;
import com.coffeeshop.model.Toppings;

import java.util.ArrayList;
import java.util.List;

public class BeverageBuilder {
    private Beverage beverage;
    private List<Toppings> toppings = new ArrayList<>();

    public BeverageBuilder(Beverage beverage){
        this.beverage = beverage;
    }

    public BeverageBuilder addTopping(Toppings topping) {
        toppings.add(topping);
        return this;
    }

    public BeverageBuilder removeTopping(Toppings topping) {
        toppings.remove(topping);
        return this;
    }

    public Beverage makeBeverage() {
        return Beverage.makeBeverage(beverage, toppings);
    }
}