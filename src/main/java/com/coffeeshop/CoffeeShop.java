package com.coffeeshop;

import java.util.List;

public class CoffeeShop {

    public static void main(String[] args) {

        WorkingShift workingShift1 = new WorkingShift();

        workingShift1
                .addOrder(new Order()
                        .addBeverage(new BeverageBuilder(new Espresso())
                                .addTopping(Toppings.MILK)
                                .addTopping(Toppings.CHOCOLATE)
                                .makeBeverage())
                        .addBeverage(new BeverageBuilder(new Americana())
                                .addTopping(Toppings.MILK)
                                .addTopping(Toppings.MILK)
                                .removeTopping(Toppings.MILK)
                                .makeBeverage())
                        .addBeverage(new Espresso())
                        .close())
                .addOrder(new Order()
                        .addBeverage(new BeverageBuilder(new Espresso())
                                .addTopping(Toppings.MILK)
                                .makeBeverage())
                        .addBeverage(new BeverageBuilder(new Espresso())
                                .addTopping(Toppings.CHOCOLATE)
                                .makeBeverage())
                        .removeLastBeverage()
                        .cancel());

        workingShift1.close();
        System.out.println(workingShift1);

        Order order1 = new Order()
                .addBeverage(new BeverageBuilder(new Espresso())
                        .addTopping(Toppings.MILK)
                        .addTopping(Toppings.CHOCOLATE)
                        .makeBeverage())
                .addBeverage(new BeverageBuilder(new Americana())
                        .addTopping(Toppings.MILK)
                        .addTopping(Toppings.MILK)
                        .removeTopping(Toppings.MILK)
                        .makeBeverage())
                .addBeverage(new Espresso())
                .close();

        System.out.println(order1);

        DbHandler dbHandler = DbHandler.getInstance();
        List<User> users = dbHandler.getUsers();

        System.out.println(users);
    }
}
