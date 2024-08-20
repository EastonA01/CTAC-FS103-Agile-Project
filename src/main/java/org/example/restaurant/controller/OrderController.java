package org.example.restaurant.controller;

/*
Connects the GUI with the OrderService.
 */

import org.example.restaurant.service.OrderService;
import org.example.restaurant.gui.OrderProcessingPanel;
import org.example.restaurant.model.Order;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class OrderController {
    private OrderService orderService;
    private OrderProcessingPanel orderProcessingPanel;

    public OrderController(OrderService orderService, OrderProcessingPanel orderProcessingPanel) {
        this.orderService = orderService;
        this.orderProcessingPanel = orderProcessingPanel;

        // TODO: Set up action listeners for order processing panel
        setupActionListeners();
        loadOrderTable();
    }

    // TODO: Implement methods to interact with OrderService
    private void setupActionListeners() {
        orderProcessingPanel.getMarkAsPreparingButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markOrderAsPreparing();
            }
        });

        orderProcessingPanel.getMarkAsReadyButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markOrderAsReady();
            }
        });
    }

    private void markOrderAsPreparing() {
        int selectedRow = orderProcessingPanel.getOrderTable().getSelectedRow();
        if (selectedRow >= 0) {
            String orderId = (String) orderProcessingPanel.getOrderTable().getValueAt(selectedRow, 0);
            orderService.updateOrderStatus(orderId, "Preparing");
            loadOrderTable();
            JOptionPane.showMessageDialog(orderProcessingPanel, "Order marked as Preparing.");
        } else {
            JOptionPane.showMessageDialog(orderProcessingPanel, "Please select an order to mark as Preparing.");
        }
    }

    private void markOrderAsReady() {
        int selectedRow = orderProcessingPanel.getOrderTable().getSelectedRow();
        if (selectedRow >= 0) {
            String orderId = (String) orderProcessingPanel.getOrderTable().getValueAt(selectedRow, 0);
            orderService.updateOrderStatus(orderId, "Ready");
            loadOrderTable();
            JOptionPane.showMessageDialog(orderProcessingPanel, "Order marked as Ready.");
        } else {
            JOptionPane.showMessageDialog(orderProcessingPanel, "Please select an order to mark as Ready.");
        }
    }

    private void loadOrderTable() {
        // Retrieve all orders from the service
        List<Order> orders = orderService.getAllOrders();

        // Prepare data for the table
        String[] columnNames = {"Order ID", "Items", "Total Price", "Status"};
        Object[][] data = new Object[orders.size()][4];
        for (int i = 0; i < orders.size(); i++) {
            data[i][0] = orders.get(i).getOrderId();
            data[i][1] = orders.get(i).getItems().toString(); // Simplified, adjust as needed
            data[i][2] = orders.get(i).getTotalPrice();
            data[i][3] = orders.get(i).getStatus();
        }

        // Update the table in the GUI
        orderProcessingPanel.updateOrderTable(data, columnNames);
    }
}

