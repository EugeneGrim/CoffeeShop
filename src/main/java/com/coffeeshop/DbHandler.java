package com.coffeeshop;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class DbHandler {
    private static final String CON_STR = "jdbc:sqlite:src/main/resources/com/coffeeshop/db.db";

    private static DbHandler instance = null;

    static synchronized DbHandler getInstance() {
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

    List<User> getUsers() {
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
}
