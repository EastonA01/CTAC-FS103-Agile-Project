package org.example.restaurant.service;

/*
Manages table assignments and statuses.
 */

import org.example.restaurant.model.Table;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TableService {
    private List<Table> tables;
    private String tableCsvFile;

    public TableService() {
        this("src/main/resources/org/example/restaurant/tables.csv");
    }

    // Constructor for testing
    public TableService(String tableCsvFile) {
        this.tableCsvFile = tableCsvFile;
        tables = new ArrayList<>();
        loadTablesFromCSV(); // Load existing tables from a CSV file
    }

    public void assignTable(int tableId, String status) {
        for (Table table : tables) {
            if (table.getTableId() == tableId) {
                table.setStatus(status); // Update the status of the table
                saveTablesToCSV(); // Save the updated table data to a CSV file
                return;
            }
        }
        // If the table does not exist, you might want to handle this case
    }

    public void updateTableStatus(int tableId, String status) {
        assignTable(tableId, status);
    }

    public List<Table> getAllTables() {
        return new ArrayList<>(tables); // Return a copy of the list
    }

    private void saveTablesToCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tableCsvFile))) {
            for (Table table : tables) {
                bw.write(table.getTableId() + "," + table.getSize() + "," + table.getStatus());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(); // Replace with logging if preferred
        }
    }

    private void loadTablesFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(tableCsvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int tableId = Integer.parseInt(values[0]);
                int size = Integer.parseInt(values[1]);
                String status = values[2];
                tables.add(new Table(tableId, size, status));
            }
        } catch (IOException e) {
            e.printStackTrace(); // Replace with logging if preferred
        }
    }
}
