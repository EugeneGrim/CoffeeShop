package com.coffeeshop;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(of="userName")
class User {
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
}
