package org.example.restaurant.controller;

import org.example.restaurant.service.OrderService;
import org.example.restaurant.service.TableService;
import org.example.restaurant.gui.OrderProcessingPanel;
import org.example.restaurant.model.Order;
import org.example.restaurant.model.Table;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class OrderController {
    private OrderService orderService;
    private TableService tableService;
    private OrderProcessingPanel orderProcessingPanel;

    public OrderController(OrderService orderService, TableService tableService, OrderProcessingPanel orderProcessingPanel) {
        this.orderService = orderService;
        this.tableService = tableService;
        this.orderProcessingPanel = orderProcessingPanel;

        setupActionListeners();
        loadOrderTable();
        populateTableDropdown();
    }

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

        orderProcessingPanel.getCreateOrderButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createOrder();
            }
        });

        orderProcessingPanel.getAssignTableButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assignTableToOrder();
            }
        });
    }

    private void markOrderAsPreparing() {
        int selectedRow = orderProcessingPanel.getOrderTable().getSelectedRow();
        if (selectedRow >= 0) {
            int orderId = (Integer) orderProcessingPanel.getOrderTable().getValueAt(selectedRow, 0);
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
            int orderId = (Integer) orderProcessingPanel.getOrderTable().getValueAt(selectedRow, 0);
            orderService.updateOrderStatus(orderId, "Ready");
            loadOrderTable();
            JOptionPane.showMessageDialog(orderProcessingPanel, "Order marked as Ready.");
        } else {
            JOptionPane.showMessageDialog(orderProcessingPanel, "Please select an order to mark as Ready.");
        }
    }

    private void createOrder() {
        String orderIdStr = JOptionPane.showInputDialog(orderProcessingPanel, "Enter Order ID:");

        if (orderIdStr != null && !orderIdStr.trim().isEmpty()) {
            int orderId = Integer.parseInt(orderIdStr);

            // Get the selected table ID from the dropdown
            int tableId = (Integer) orderProcessingPanel.getTableDropdown().getSelectedItem();

            // Show the food selection dialog
            String[] foodChoices = {"Pizza", "Burger", "Pasta", "Fries"};
            String food = (String) JOptionPane.showInputDialog(
                    orderProcessingPanel,
                    "Select Food Item:",
                    "Food Selection",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    foodChoices,
                    foodChoices[0]
            );

            // Assuming price for each food item (you can adjust these values)
            double price = 0.0;
            switch (food) {
                case "Pizza":
                    price = 10.0;
                    break;
                case "Burger":
                    price = 7.5;
                    break;
                case "Pasta":
                    price = 8.0;
                    break;
                case "Fries":
                    price = 2.5;
                    break;
            }

            Order order = new Order(orderId, tableId);
            order.addItem(food, 1, price);
            orderService.addOrder(order);
            loadOrderTable();
            JOptionPane.showMessageDialog(orderProcessingPanel, "Order created successfully.");
        } else {
            JOptionPane.showMessageDialog(orderProcessingPanel, "Order ID cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void assignTableToOrder() {
        int selectedRow = orderProcessingPanel.getOrderTable().getSelectedRow();
        if (selectedRow >= 0) {
            int orderId = (Integer) orderProcessingPanel.getOrderTable().getValueAt(selectedRow, 0);
            int tableId = (Integer) orderProcessingPanel.getTableDropdown().getSelectedItem();

            Order order = orderService.getAllOrders().stream()
                    .filter(o -> o.getOrderId() == orderId)
                    .findFirst()
                    .orElse(null);

            if (order != null) {
                Table table = tableService.getTableById(tableId);
                if (table != null) {
                    table.addOrder(order);
                    table.setTotalPrice(table.getTotalPrice() + order.getTotalPrice());
                    tableService.addTable(table);
                    loadOrderTable();
                    JOptionPane.showMessageDialog(orderProcessingPanel, "Order assigned to Table successfully.");
                } else {
                    JOptionPane.showMessageDialog(orderProcessingPanel, "Table not found.");
                }
            } else {
                JOptionPane.showMessageDialog(orderProcessingPanel, "Order not found.");
            }
        } else {
            JOptionPane.showMessageDialog(orderProcessingPanel, "Please select an order to assign to a table.");
        }
    }

    private void loadOrderTable() {
        List<Order> orders = orderService.getAllOrders();
        String[] columnNames = {"Order ID", "Food", "Total Price", "Status"};
        Object[][] data = new Object[orders.size()][4];
        for (int i = 0; i < orders.size(); i++) {
            data[i][0] = orders.get(i).getOrderId();
            data[i][1] = orders.get(i).getItems().toString();
            data[i][2] = orders.get(i).getTotalPrice();
            data[i][3] = orders.get(i).getStatus();
        }
        orderProcessingPanel.updateOrderTable(data, columnNames);
    }

    private void populateTableDropdown() {
        List<Table> tables = tableService.getAllTables();
        Integer[] tableIds = tables.stream().map(Table::getTableId).toArray(Integer[]::new);
        orderProcessingPanel.populateTableDropdown(tableIds);
    }
}




