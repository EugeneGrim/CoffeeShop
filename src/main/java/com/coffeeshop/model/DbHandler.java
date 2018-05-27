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

    public List<BeverageDecorator> getToppings() {
        try (Statement statement = this.connection.createStatement()) {
            List<BeverageDecorator> toppings = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM toppings");
            while (resultSet.next()) {
                BeverageDecorator topping = new BeverageDecorator();
                topping.setName(resultSet.getString("topping_name"));
                topping.setDescription(resultSet.getString("topping_description"));
                topping.setCost(resultSet.getDouble("topping_cost"));
                toppings.add(topping);
            }
            return toppings;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<BeverageDecorator.Topping> getToppingsN() {
        try (Statement statement = this.connection.createStatement()) {
            List<BeverageDecorator.Topping> toppings = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM toppings");
            while (resultSet.next()) {
                toppings.add(new BeverageDecorator.Topping(
                        resultSet.getString("topping_name"),
                        resultSet.getString("beverage_description"),
                        resultSet.getDouble("beverage_cost")
                ));
            }
            return toppings;
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
