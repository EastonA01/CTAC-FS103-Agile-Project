package org.example.restaurant;

/*
Implement the Main class to start the application by showing the LoginFrame.

Ensure data persistence by loading data from files at startup and saving data on exit.
 */

import org.example.restaurant.gui.LoginFrame;
import org.example.restaurant.service.UserService;
import org.example.restaurant.controller.LoginController;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        LoginFrame loginFrame = new LoginFrame();
        LoginController loginController = new LoginController(userService, loginFrame);

        // TODO: Initialize other services and controllers as needed

        loginFrame.setVisible(true);
    }
}
