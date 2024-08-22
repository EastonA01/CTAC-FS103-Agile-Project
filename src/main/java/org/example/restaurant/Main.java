package org.example.restaurant;

import org.example.restaurant.gui.LoginFrame;
import org.example.restaurant.controller.LoginController;
import org.example.restaurant.model.MenuItem;
import org.example.restaurant.model.Order;
import org.example.restaurant.service.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Initialize Objects and List Variables
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        List<Order> orders = new ArrayList<Order>();


        // Initialize services
        UserService userService = new UserService();
        MenuService menuService = new MenuService();
        InventoryService inventoryService = new InventoryService();
        OrderService orderService = new OrderService();
        TableService tableService = new TableService();
        SalesReportService salesReport = new SalesReportService(orders, menuItems);

        LoginFrame loginFrame = new LoginFrame();
        new LoginController(userService, loginFrame);

        loginFrame.setVisible(true);
    }
}
