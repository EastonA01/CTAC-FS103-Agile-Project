package org.example.restaurant.controller;

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

        setupActionListeners();
        loadTableData();
    }

    private void setupActionListeners() {
        tableManagementPanel.getSaveTableButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTable();
            }
        });

        tableManagementPanel.getDeleteTableButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTable();
            }
        });
    }

    private void loadTableData() {
        List<Table> tables = tableService.getAllTables();
        Object[][] data = new Object[tables.size()][5];

        for (int i = 0; i < tables.size(); i++) {
            Table table = tables.get(i);
            data[i][0] = table.getTableId();
            data[i][1] = table.getTableName();
            data[i][2] = table.getOccupants();
            data[i][3] = table.getStatus();
            data[i][4] = table.getTotalPrice();
        }

        tableManagementPanel.updateTableData(data);
    }

    private void saveTable() {
        try {
            int tableId = Integer.parseInt(tableManagementPanel.getTableIdField().getText());
            String tableName = tableManagementPanel.getTableNameField().getText();
            int occupants = Integer.parseInt(tableManagementPanel.getOccupantsField().getText());
            String status = tableManagementPanel.getStatusComboBox().getSelectedItem().toString();

            Table table = new Table(tableId, tableName, occupants, status);
            tableService.addTable(table);
            loadTableData();  // Refresh the table display
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(tableManagementPanel, "Please enter valid numeric values for Table ID and Occupants.");
        }
    }

    private void deleteTable() {
        int selectedRow = tableManagementPanel.getTable().getSelectedRow();

        if (selectedRow != -1) {
            // Delete based on selected row
            int tableId = (int) tableManagementPanel.getTable().getValueAt(selectedRow, 0);
            tableService.deleteTable(tableId);
            loadTableData();  // Refresh the table display
        } else {
            try {
                // Delete based on Table ID input
                int tableId = Integer.parseInt(tableManagementPanel.getTableIdField().getText());
                tableService.deleteTable(tableId);
            } catch (NumberFormatException ex) {
                // If Table ID is not provided, try deleting based on Table Name
                String tableName = tableManagementPanel.getTableNameField().getText();
                if (!tableName.isEmpty()) {
                    tableService.deleteTableByName(tableName);
                } else {
                    JOptionPane.showMessageDialog(tableManagementPanel, "Please select a row or enter a Table ID/Name to delete.");
                    return;
                }
            }
            loadTableData();  // Refresh the table display
        }
    }
}
