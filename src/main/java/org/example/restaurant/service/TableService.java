package org.example.restaurant.service;

import org.example.restaurant.model.Order;
import org.example.restaurant.model.Table;

import java.util.ArrayList;
import java.util.List;

public class TableService {
    private List<Table> tables;

    public TableService() {
        tables = new ArrayList<>();
        loadTablesFromCSV(); // Load existing tables from a CSV file
    }

    public void addTable(Table table) {
        boolean found = false;
        for (int i = 0; i < tables.size(); i++) {
            if (tables.get(i).getTableId() == table.getTableId()) {
                tables.set(i, table);
                found = true;
                break;
            }
        }
        if (!found) {
            tables.add(table);
        }
        saveTablesToCSV(); // Save the updated tables to a CSV file
    }

    public void addOrderToTable(int tableId, Order order) {
        for (Table table : tables) {
            if (table.getTableId() == tableId) {
                table.addOrder(order);
                saveTablesToCSV();
                break;
            }
        }
    }

    public List<Table> getAllTables() {
        return new ArrayList<>(tables);
    }

    private void saveTablesToCSV() {
        // Implementation to save tables to a CSV file
    }

    private void loadTablesFromCSV() {
        // Implementation to load tables from a CSV file
    }
}
