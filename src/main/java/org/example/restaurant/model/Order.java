package org.example.restaurant.model;

/*
Represents an order with attributes like order ID, list of items, total price, status.
 */

import java.util.List;

public class Order {
    private String orderId;
    private List<MenuItem> items;
    private double totalPrice;
    private String status;

    public Order(String orderId, List<MenuItem> items, double totalPrice, String status) {
        this.orderId = orderId;
        this.items = items;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<MenuItem> getItems() {
        return items;
    }
    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
