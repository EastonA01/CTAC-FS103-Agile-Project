package org.example.restaurant.controller;

/*
Handles login logic.
 */

import org.example.restaurant.service.UserService;
import org.example.restaurant.gui.LoginFrame;

public class LoginController {
    private UserService userService;
    private LoginFrame loginFrame;

    public LoginController(UserService userService, LoginFrame loginFrame) {
        this.userService = userService;
        this.loginFrame = loginFrame;

        // TODO: Set up action listeners for login frame
    }

    // TODO: Implement methods to handle login logic
}
