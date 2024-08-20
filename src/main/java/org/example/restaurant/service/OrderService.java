package org.example.restaurant.service;

/*
Handles order processing, updating order statuses, and calculating total prices.
 */

import org.example.restaurant.model.MenuItem;
import org.example.restaurant.model.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<Order> orders;
    private static final String ORDERS_CSV_FILE = "src/main/resources/org/example/restaurant/orders.csv";

    public OrderService() {
        // Initialize orders list (maybe load from file)
        orders = new ArrayList<>();
        loadOrdersFromCSV(); // Load existing orders from a CSV file
    }

    public void addOrder(Order order) {
        // Add a new order
        orders.add(order);
        saveOrdersToCSV(); // Save the updated orders list to a CSV file
    }

    public void updateOrderStatus(String orderId, String status) {
        // Update the status of an order by ID
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                order.setStatus(status);
                saveOrdersToCSV(); // Save the updated orders list to a CSV file
                break;
            }
        }
    }

    public List<Order> getAllOrders() {
        // Return all orders
        return new ArrayList<>(orders); // Return a copy of the list
    }

    // Implement methods to load and save orders to file
    private void saveOrdersToCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ORDERS_CSV_FILE))) {
            for (Order order : orders) {
                bw.write(order.getOrderId() + "," + convertItemsToString(order.getItems()) + "," +
                        order.getTotalPrice() + "," + order.getStatus());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(); // Replace with logging if preferred
        }
    }

    private void loadOrdersFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(ORDERS_CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",", 4);
                String orderId = values[0];
                List<MenuItem> items = parseItemsString(values[1]);
                double totalPrice = Double.parseDouble(values[2]);
                String status = values[3];
                orders.add(new Order(orderId, items, totalPrice, status));
            }
        } catch (IOException e) {
            e.printStackTrace(); // Replace with logging if preferred
        }
    }

    private String convertItemsToString(List<MenuItem> items) {
        StringBuilder sb = new StringBuilder();
        for (MenuItem item : items) {
            sb.append(item.getName()).append(";");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1); // Remove last semicolon
        }
        return sb.toString();
    }

    private List<MenuItem> parseItemsString(String itemsString) {
        List<MenuItem> items = new ArrayList<>();
        String[] itemNames = itemsString.split(";");
        for (String name : itemNames) {
            items.add(new MenuItem(name, "", 0.0, new ArrayList<>())); // Placeholder for simplicity
        }
        return items;
    }
}

