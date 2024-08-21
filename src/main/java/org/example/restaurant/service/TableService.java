package org.example.restaurant.service;

import org.example.restaurant.model.Table;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TableService {
    private List<Table> tables;
    private static final String TABLE_CSV_FILE = "src/main/resources/org/example/restaurant/tables.csv";

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

    public void deleteTable(int tableId) {
        tables.removeIf(table -> table.getTableId() == tableId);
        saveTablesToCSV(); // Save the updated tables to a CSV file
    }

    public List<Table> getAllTables() {
        return new ArrayList<>(tables); // Return a copy of the list
    }

    private void saveTablesToCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TABLE_CSV_FILE))) {
            for (Table table : tables) {
                bw.write(table.getTableId() + "," + table.getTableName() + "," + table.getOccupants() + "," + table.getStatus() + "," + table.getTotalPrice());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions by printing to the console or logging
        }
    }

    private void loadTablesFromCSV() {
        // Your implementation for loading tables from a CSV file
        // This method should read the CSV and populate the `tables` list
    }

    // Additional methods for calculating total sales and item quantities (as shown earlier)
}
