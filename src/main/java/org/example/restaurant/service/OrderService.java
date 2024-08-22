package org.example.restaurant.service;

import org.example.restaurant.model.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class OrderService {
    private List<Order> orders;
    private static final String ORDERS_CSV_FILE = "src/main/resources/org/example/restaurant/orders.csv";

    public OrderService() {
        orders = new ArrayList<>();
        loadOrdersFromCSV(); // Load existing orders from a CSV file
    }

    public void addOrder(Order order) {
        orders.add(order);
        saveOrdersToCSV(); // Save the updated orders list to a CSV file
    }

    public void updateOrderStatus(int orderId, String status) {
        // Since your original Order class doesn't have a status, this is a placeholder
        // to demonstrate where you would update the order's status if you implemented it.
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders); // Return a copy of the list
    }

    private void saveOrdersToCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ORDERS_CSV_FILE))) {
            for (Order order : orders) {
                bw.write(order.getOrderId() + "," + convertItemsToString(order.getItems()) + "," +
                        order.getTotalPrice());
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
                String[] values = line.split(",", 3);
                int orderId = Integer.parseInt(values[0]);
                int tableId = Integer.parseInt(values[1]);
                Map<String, Integer> items = parseItemsString(values[2]);
                double totalPrice = Double.parseDouble(values[3]);
                Order order = new Order(orderId, tableId);
                order.getItems().putAll(items); // Add the items to the order
                orders.add(order);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Replace with logging if preferred
        }
    }

    private String convertItemsToString(Map<String, Integer> items) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            sb.append(entry.getKey()).append(":").append(entry.getValue()).append(";");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1); // Remove last semicolon
        }
        return sb.toString();
    }

    private Map<String, Integer> parseItemsString(String itemsString) {
        Map<String, Integer> items = new HashMap<>();
        String[] itemPairs = itemsString.split(";");
        for (String pair : itemPairs) {
            String[] itemData = pair.split(":");
            String itemName = itemData[0];
            int quantity = Integer.parseInt(itemData[1]);
            items.put(itemName, quantity);
        }
        return items;
    }
}
