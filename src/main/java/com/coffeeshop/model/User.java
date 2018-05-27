package com.coffeeshop.model;

import lombok.Getter;
import lombok.Setter;

@Getter
public class User {
    private int userId;
    @Setter private String userName;
    @Setter private String userLogin;
    @Setter private UserRole userRole;

    enum UserRole {
        ADMIN,
        USER
    }
    User(int userId, String userName, String userLogin, UserRole userRole) {
        this.userId = userId;
        this.userName = userName;
        this.userLogin = userLogin;
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return userName;
    }
}
