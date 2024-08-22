package org.example.restaurant.controller;

import org.example.restaurant.gui.TableManagementPanel;
import org.example.restaurant.model.Order;
import org.example.restaurant.model.Table;
import org.example.restaurant.service.TableService;

import javax.swing.*;

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
        tableManagementPanel.getSaveTableButton().addActionListener(e -> saveTable());
    }

    private void loadTableData() {
        Object[][] tableData = convertTableListToObjectArray(tableService.getAllTables());
        tableManagementPanel.updateTableData(tableData);
    }

    private void saveTable() {
        int tableId = Integer.parseInt(tableManagementPanel.getTableIdField().getText());
        String tableName = tableManagementPanel.getTableNameField().getText();
        int occupants = Integer.parseInt(tableManagementPanel.getOccupantsField().getText());
        String status = tableManagementPanel.getStatusComboBox().getSelectedItem().toString();

        // Check if the Table ID already exists
        for (Table existingTable : tableService.getAllTables()) {
            if (existingTable.getTableId() == tableId) {
                JOptionPane.showMessageDialog(tableManagementPanel, "Table ID " + tableId + " is already in use.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        Table table = new Table(tableId, tableName, occupants, status);
        tableService.addTable(table);

        loadTableData(); // Refresh the table display
    }

    public void addOrderToTable(int tableId, Order order) {
        tableService.addOrderToTable(tableId, order);
        loadTableData(); // Refresh the table display
    }

    private Object[][] convertTableListToObjectArray(java.util.List<Table> tables) {
        Object[][] tableData = new Object[tables.size()][5];
        for (int i = 0; i < tables.size(); i++) {
            Table table = tables.get(i);
            tableData[i][0] = table.getTableId();
            tableData[i][1] = table.getTableName();
            tableData[i][2] = table.getOccupants();
            tableData[i][3] = table.getStatus();
            tableData[i][4] = table.getTotalSales();  // Use getTotalSales() method to show total price
        }
        return tableData;
    }
}
