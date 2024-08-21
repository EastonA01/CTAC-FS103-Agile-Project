package org.example.restaurant.model;

import java.util.HashMap;
import java.util.Map;

public class Table {
    private int tableId;
    private String tableName;
    private int occupants;
    private String status;
    private double totalPrice;
    private Map<String, Integer> items; // Map of item name to quantity

    public Table(int tableId, String tableName, int occupants, String status, double totalPrice) {
        this.tableId = tableId;
        this.tableName = tableName;
        this.occupants = occupants;
        this.status = status;
        this.totalPrice = totalPrice;
        this.items = new HashMap<>();
    }

    // Getters and setters
    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getOccupants() {
        return occupants;
    }

    public void setOccupants(int occupants) {
        this.occupants = occupants;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void addItem(String itemName, int quantity, double pricePerUnit) {
        items.put(itemName, items.getOrDefault(itemName, 0) + quantity);
        this.totalPrice += quantity * pricePerUnit; // Update total price based on the new items
    }

    public int getItemQuantity(String itemName) {
        return items.getOrDefault(itemName, 0);
    }

    public double calculateTotalSales() {
        return this.totalPrice;
    }
}
