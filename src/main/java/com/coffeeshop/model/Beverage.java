package com.coffeeshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.ResourceBundle;

public class Beverage extends Goods {
    private final static double DRINK_MARGIN;

    enum BeverageSize {
        SMALL,
        STANDART,
        BIG
    }

    @Setter
    @Getter
    private BeverageSize beverageSize = BeverageSize.STANDART;

    static {
        ResourceBundle myResources = ResourceBundle.getBundle("com.coffeeshop.Margins");
        DRINK_MARGIN = Double.parseDouble(myResources.getString("DRINK_MARGIN"));
    }

    private static Beverage addTopping(Beverage beverage, BeverageDecorator topping) {
        topping.setBeverage(beverage);
        return topping;
    }

    private static Beverage addTopping(Beverage beverage, Toppings topping) {
        switch (topping) {
            case MILK:
                beverage = new Milk(beverage);
                break;
            case CHOCOLATE:
                beverage = new Chocolate(beverage);
                break;
            case WHIPPED_MILK:
                beverage = new WhippedMilk(beverage);
                break;
        }
        return beverage;
    }

    public static Beverage makeBeverage(Beverage beverage, List<Toppings> toppingsList) {
        for (Toppings topping : toppingsList) {
            beverage = Beverage.addTopping(beverage, topping);
        }
        return beverage;
    }

    @Override
    public double getPrice() {
        return getCost() * DRINK_MARGIN;
    }
}

