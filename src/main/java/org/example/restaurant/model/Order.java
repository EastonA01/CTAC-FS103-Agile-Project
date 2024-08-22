package org.example.restaurant.model;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private int orderId;
    private int tableId;
    private Map<String, Integer> items;  // Item name to quantity
    private double totalPrice;

    public Order(int orderId, int tableId) {
        this.orderId = orderId;
        this.tableId = tableId;
        this.items = new HashMap<>();
        this.totalPrice = 0.0;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getTableId() {
        return tableId;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void addItem(String itemName, int quantity, double pricePerUnit) {
        items.put(itemName, items.getOrDefault(itemName, 0) + quantity);
        totalPrice += quantity * pricePerUnit;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    // Optional: If you want to track the status in the future, you can add this method
    public String getStatus() {
        return "Completed";  // Placeholder status, as the original class doesn't track status
    }
}
