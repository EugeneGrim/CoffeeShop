package com.coffeeshop;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.ResourceBundle;

@Setter
@Getter
abstract class Beverage {
    protected final static double DRINK_MARGIN;
    protected final static double TOPPING_MARGIN;

    static {
        ResourceBundle myResources = ResourceBundle.getBundle("com.coffeeshop.Margins");
        DRINK_MARGIN = Double.parseDouble(myResources.getString("DRINK_MARGIN"));
        TOPPING_MARGIN = Double.parseDouble(myResources.getString("TOPPING_MARGIN"));
    }

    private double cost;
    private String name;
    private String description;

    private static Beverage addTopping(Beverage beverage, Toppings toppings) {
        switch (toppings) {
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

    static Beverage makeBeverage(Beverage beverage, List<Toppings> toppingsList) {
        for (Toppings topping : toppingsList) {
            beverage = Beverage.addTopping(beverage, topping);
        }
        return beverage;
    }

    public double getPrice() {
        return cost * DRINK_MARGIN;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Espresso extends Beverage {
    Espresso() {
        this.setName("Espresso");
        this.setCost(25.0);
    }
}

class Americana extends Beverage {
    Americana() {
        this.setName("Americana");
        this.setCost(25.0);
    }
}

class BlackTea extends Beverage {
    BlackTea() {
        this.setName("Black tea");
        this.setCost(16.0);
    }
}

class GreenTea extends Beverage {
    GreenTea() {
        this.setName("Green tea");
        this.setCost(16.0);
    }
}
