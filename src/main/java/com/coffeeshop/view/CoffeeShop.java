package com.coffeeshop.view;

import com.coffeeshop.controller.BeverageBuilder;
import com.coffeeshop.controller.Order;
import com.coffeeshop.controller.WorkingShift;
import com.coffeeshop.model.*;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CoffeeShop {
    private List<User> users;
    private List<Beverage> beverages;
    private List<BeverageDecorator> toppings;
    private List<Snack> snacks;
    private List<HotFood> hotFood;

    public static void main(String[] args) {

        DbHandler dbHandler = DbHandler.getInstance();

        CoffeeShop coffeeShop = new CoffeeShop();
        coffeeShop.setUsers(dbHandler.getUsers());
        coffeeShop.setBeverages(dbHandler.getBeverages());
        coffeeShop.setToppings(dbHandler.getToppings());

        WorkingShift workingShift1 = new WorkingShift();

        Order order1 = new Order()
                .addBeverage(new BeverageBuilder(coffeeShop.getBeverages().get(0))
                        .addTopping(Toppings.MILK)
                        .addTopping(Toppings.CHOCOLATE)
                        .makeBeverage())
                .addBeverage(new BeverageBuilder(coffeeShop.getBeverages().get(1))
                        .addTopping(Toppings.MILK)
                        .addTopping(Toppings.MILK)
                        .removeTopping(Toppings.MILK)
                        .makeBeverage())
                .addBeverage(coffeeShop.getBeverages().get(0))
                .close();

        System.out.println(order1);

        workingShift1.addOrder(order1);

        workingShift1
                .addOrder(new Order()
                        .addBeverage(new BeverageBuilder(coffeeShop.getBeverages().get(0))
                                .addTopping(Toppings.MILK)
                                .addTopping(Toppings.CHOCOLATE)
                                .makeBeverage())
                        .addBeverage(new BeverageBuilder(coffeeShop.getBeverages().get(1))
                                .addTopping(Toppings.MILK)
                                .addTopping(Toppings.MILK)
                                .removeTopping(Toppings.MILK)
                                .makeBeverage())
                        .addBeverage(coffeeShop.getBeverages().get(0))
                        .close())
                .addOrder(new Order()
                        .addBeverage(new BeverageBuilder(coffeeShop.getBeverages().get(0))
                                .addTopping(Toppings.MILK)
                                .makeBeverage())
                        .addBeverage(new BeverageBuilder(coffeeShop.getBeverages().get(0))
                                .addTopping(Toppings.CHOCOLATE)
                                .makeBeverage())
                        .removeLastBeverage()
                        .cancel());

        workingShift1.close();

        System.out.println(workingShift1);
    }
}
