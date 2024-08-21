package org.example.restaurant.util;

import org.example.restaurant.model.Table;
import org.example.restaurant.service.TableService;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class TableServiceTest {

    private TableService tableService;

    @Before
    public void setUp() throws Exception {
        // Initialize TableService
        tableService = new TableService();

        // Use reflection to access the private `tables` field
        Field tablesField = TableService.class.getDeclaredField("tables");
        tablesField.setAccessible(true);

        // Manually initialize the `tables` list for testing purposes
        List<Table> testTables = new ArrayList<>();
        testTables.add(new Table(1, 4, "Available"));
        testTables.add(new Table(2, 2, "Occupied"));

        // Set the `tables` field in the `tableService` instance
        tablesField.set(tableService, testTables);
    }

    @Test
    public void testGetAllTables() {
        // Act
        List<Table> tables = tableService.getAllTables();

        // Assert
        assertEquals(2, tables.size());
        assertEquals(1, tables.get(0).getTableId());
        assertEquals("Available", tables.get(0).getStatus());
        assertEquals(2, tables.get(1).getTableId());
        assertEquals("Occupied", tables.get(1).getStatus());
    }

    @Test
    public void testAssignTable() {
        // Act
        tableService.assignTable(1, "Reserved");

        // Assert
        List<Table> tables = tableService.getAllTables();
        assertEquals("Reserved", tables.get(0).getStatus());
    }

    @Test
    public void testUpdateTableStatus() {
        // Act
        tableService.updateTableStatus(2, "Available");

        // Assert
        List<Table> tables = tableService.getAllTables();
        assertEquals("Available", tables.get(1).getStatus());
    }
}
