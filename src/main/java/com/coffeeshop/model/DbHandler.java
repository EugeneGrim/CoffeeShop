package com.coffeeshop.model;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DbHandler {
    private static final String CON_STR = "jdbc:sqlite:src/main/resources/com/coffeeshop/db.db";

    private static DbHandler instance = null;

    public static synchronized DbHandler getInstance() {
        if (instance == null)
            instance = new DbHandler();
        return instance;
    }

    private Connection connection;

    private DbHandler() {
        try {
            DriverManager.registerDriver(new JDBC());
            this.connection = DriverManager.getConnection(CON_STR);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getUsers() {
        try (Statement statement = this.connection.createStatement()) {
            List<User> users = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT user_id, user_role, user_name, user_login FROM users");
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("user_id"),
                        resultSet.getString("user_name"),
                        resultSet.getString("user_login"),
                        resultSet.getInt("user_role") == 0 ? User.UserRole.ADMIN : User.UserRole.USER));
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Beverage> getBeverages() {
        try (Statement statement = this.connection.createStatement()) {
            List<Beverage> beverages= new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM beverages");
            while (resultSet.next()) {
                Beverage beverage = new Beverage();
                beverage.setName(resultSet.getString("beverage_name"));
                beverage.setDescription(resultSet.getString("beverage_description"));
                beverage.setCost(resultSet.getDouble("beverage_cost"));
                beverages.add(beverage);
            }
            return beverages;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<BeverageDecorator.Topping> getToppings() {
        try (Statement statement = this.connection.createStatement()) {
            List<BeverageDecorator.Topping> toppings = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM toppings");
            while (resultSet.next()) {
                toppings.add(new BeverageDecorator.Topping(
                        resultSet.getString("topping_name"),
                        resultSet.getString("topping_description"),
                        resultSet.getDouble("topping_cost")
                ));
            }
            return toppings;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<Snack> getSnacks() {
        try (Statement statement = this.connection.createStatement()) {
            List<Snack> snacks = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM snacks");
            while (resultSet.next()) {
                Snack snack = new Snack();
                snack.setName(resultSet.getString("snack_name"));
                snack.setDescription(resultSet.getString("snack_description"));
                snack.setCost(resultSet.getDouble("snack_cost"));
                snacks.add(snack);
            }
            return snacks;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<HotFood> getHotFoods() {
        try (Statement statement = this.connection.createStatement()) {
            List<HotFood> hotFoods = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM food");
            while (resultSet.next()) {
                HotFood hotFood = new HotFood();
                hotFood.setName(resultSet.getString("food_name"));
                hotFood.setDescription(resultSet.getString("food_description"));
                hotFood.setCost(resultSet.getDouble("food_cost"));
                hotFoods.add(hotFood);
            }
            return hotFoods;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
