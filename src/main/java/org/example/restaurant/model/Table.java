package org.example.restaurant.model;

/*
Represents a table with properties like table ID, size, status.
 */

public class Table {
    private int tableId;
    private int size;
    private String status;

    public Table(int tableId, int size, String status) {
        this.tableId = tableId;
        this.size = size;
        this.status = status;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}