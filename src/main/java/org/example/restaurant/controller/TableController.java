package org.example.restaurant.controller;

/*
Connects the GUI with the TableService.
 */

import org.example.restaurant.service.TableService;
import org.example.restaurant.gui.TableManagementPanel;
import org.example.restaurant.model.Table;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TableController {
    private TableService tableService;
    private TableManagementPanel tableManagementPanel;

    public TableController(TableService tableService, TableManagementPanel tableManagementPanel) {
        this.tableService = tableService;
        this.tableManagementPanel = tableManagementPanel;

        // Set up action listeners for table management panel
        setupActionListeners();
        loadTableStatusTable();
    }

    private void setupActionListeners() {
        tableManagementPanel.getAssignTableButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assignTable();
            }
        });
    }

    private void assignTable() {
        // Collect data from GUI
        int tableId;
        try {
            tableId = Integer.parseInt(tableManagementPanel.getTableIdField().getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(tableManagementPanel, "Please enter a valid table ID.");
            return;
        }

        String status = (String) tableManagementPanel.getStatusComboBox().getSelectedItem();

        // Update table status via the service
        tableService.updateTableStatus(tableId, status);

        // Update GUI
        loadTableStatusTable();
        JOptionPane.showMessageDialog(tableManagementPanel, "Table status updated successfully!");
    }

    private void loadTableStatusTable() {
        // Retrieve all tables from the service
        List<Table> tables = tableService.getAllTables();

        // Prepare data for the table
        String[] columnNames = {"Table ID", "Size", "Status"};
        Object[][] data = new Object[tables.size()][3];
        for (int i = 0; i < tables.size(); i++) {
            data[i][0] = tables.get(i).getTableId();
            data[i][1] = tables.get(i).getSize();
            data[i][2] = tables.get(i).getStatus();
        }

        // Update the table in the GUI
        tableManagementPanel.updateTableStatusTable(data, columnNames);
    }
}

