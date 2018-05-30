package com.coffeeshop.view;

import com.coffeeshop.controller.BeverageBuilder;
import com.coffeeshop.model.Order;
import com.coffeeshop.model.WorkingShift;
import com.coffeeshop.model.*;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CoffeeShop {
    private List<User> users;
    private List<Beverage> beverages;
    private List<BeverageDecorator.Topping> toppings;
    private List<Snack> snacks;
    private List<HotFood> hotFoods;

    public static void main(String[] args) {

        DbHandler dbHandler = DbHandler.getInstance();

        CoffeeShop coffeeShop = new CoffeeShop();
        coffeeShop.setUsers(dbHandler.getUsers());
        coffeeShop.setBeverages(dbHandler.getBeverages());
        coffeeShop.setToppings(dbHandler.getToppings());
        coffeeShop.setSnacks(dbHandler.getSnacks());
        coffeeShop.setHotFoods(dbHandler.getHotFoods());

        WorkingShift workingShift1 = new WorkingShift(coffeeShop.getUsers().get(0));

        Order order1 = new Order(0)
                .addBeverage(new BeverageBuilder(coffeeShop.getBeverages().get(0))
                        .addTopping(coffeeShop.getToppings().get(0))
                        .addTopping(coffeeShop.getToppings().get(1))
                        .makeBeverage())
                .addBeverage(new BeverageBuilder(coffeeShop.getBeverages().get(1))
                        .addTopping(coffeeShop.getToppings().get(0))
                        .addTopping(coffeeShop.getToppings().get(0))
                        .removeTopping(coffeeShop.getToppings().get(0))
                        .makeBeverage())
                .addBeverage(coffeeShop.getBeverages().get(0))
                .addSnack(coffeeShop.getSnacks().get(0))
                .close();

        System.out.println(order1);

        workingShift1.addOrder(order1);

        workingShift1
                .addOrder(new Order(1)
                        .addBeverage(new BeverageBuilder(coffeeShop.getBeverages().get(0))
                                .addTopping(coffeeShop.getToppings().get(0))
                                .addTopping(coffeeShop.getToppings().get(1))
                                .makeBeverage())
                        .addBeverage(new BeverageBuilder(coffeeShop.getBeverages().get(1))
                                .addTopping(coffeeShop.getToppings().get(0))
                                .addTopping(coffeeShop.getToppings().get(0))
                                .removeTopping(coffeeShop.getToppings().get(0))
                                .makeBeverage())
                        .addBeverage(coffeeShop.getBeverages().get(0))
                        .close())
                .addOrder(new Order(2)
                        .addBeverage(new BeverageBuilder(coffeeShop.getBeverages().get(0))
                                .addTopping(coffeeShop.getToppings().get(0))
                                .makeBeverage())
                        .addBeverage(new BeverageBuilder(coffeeShop.getBeverages().get(0))
                                .addTopping(coffeeShop.getToppings().get(1))
                                .makeBeverage())
                        .removeLastBeverage()
                        .cancel());

        workingShift1.close();

        System.out.println(workingShift1);
    }
}
