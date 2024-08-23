package org.example.restaurant.util;

import org.example.restaurant.service.TableService;
import org.example.restaurant.model.Table;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TableServiceTest {

    private TableService tableService;
    private static final String TEST_CSV_FILE = "src/test/resources/org/example/restaurant/tables_test.csv";

    @Before
    public void setUp() {
        tableService = Mockito.spy(new TableService());
        doNothing().when(tableService).loadTablesFromCSV();
        doNothing().when(tableService).saveTablesToCSV();
    }


    private void clearCSVFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                Files.write(Paths.get(filePath), "".getBytes());
            } else {
                Files.createDirectories(Paths.get(filePath).getParent());
                Files.createFile(Paths.get(filePath));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddTable() {
        Table table = new Table(1, "Table 1", 4, "Occupied");
        tableService.addTable(table);

        List<Table> tables = tableService.getAllTables();
        assertEquals(1, tables.size());
        assertEquals("Table 1", tables.get(0).getTableName());
    }

    @Test
    public void testDeleteTable() {
        Table table = new Table(1, "Table 1", 4, "Occupied");
        tableService.addTable(table);

        tableService.deleteTable(1);

        List<Table> tables = tableService.getAllTables();
        assertTrue(tables.isEmpty());
    }

    @Test
    public void testGetTableById() {
        Table table = new Table(1, "Table 1", 4, "Occupied");
        tableService.addTable(table);

        Table retrievedTable = tableService.getTableById(1);
        assertNotNull(retrievedTable);
        assertEquals("Table 1", retrievedTable.getTableName());
    }

    @Test
    public void testDeleteTableByName() {
        Table table = new Table(1, "Table 1", 4, "Occupied");
        tableService.addTable(table);

        tableService.deleteTableByName("Table 1");

        List<Table> tables = tableService.getAllTables();
        assertTrue(tables.isEmpty());
    }
}
