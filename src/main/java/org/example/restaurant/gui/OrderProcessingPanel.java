package org.example.restaurant.gui;

/*
Interface for processing orders.
 */

import javax.swing.*;
import java.awt.*;

public class OrderProcessingPanel extends JPanel {
    // Components like tables, labels, buttons
    private JTable orderTable;
    private JButton markAsPreparingButton;
    private JButton markAsReadyButton;

    public OrderProcessingPanel() {
        // TODO: Initialize components
        initializeComponents();

        // TODO: Add components to panel
        addComponentsToPanel();

        // TODO: Set up action listeners for buttons
        // Note: Action listeners will typically be set up in the controller
    }

    private void initializeComponents() {
        orderTable = new JTable(); // This will be populated with order data
        markAsPreparingButton = new JButton("Mark as Preparing");
        markAsReadyButton = new JButton("Mark as Ready");
    }

    private void addComponentsToPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(new JScrollPane(orderTable), gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0;
        add(markAsPreparingButton, gbc);

        gbc.gridx = 1;
        add(markAsReadyButton, gbc);
    }

    // Implement methods to update order statuses
    public JButton getMarkAsPreparingButton() {
        return markAsPreparingButton;
    }

    public JButton getMarkAsReadyButton() {
        return markAsReadyButton;
    }

    public JTable getOrderTable() {
        return orderTable;
    }

    public void updateOrderTable(Object[][] data, String[] columnNames) {
        orderTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }
}

