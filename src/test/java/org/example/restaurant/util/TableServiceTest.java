import org.example.restaurant.model.Table;
import org.example.restaurant.service.TableService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TableServiceTest {

    private TableService tableService;

    @Before
    public void setUp() {
        tableService = new TableService();
    }

    @Test
    public void testAddTable() {
        Table table = new Table(1, "Table 1", 4, "Occupied", 0.0);
        tableService.addTable(table);

        List<Table> tables = tableService.getAllTables();
        assertEquals(1, tables.size());
        assertEquals("Table 1", tables.get(0).getTableName());
    }

    @Test
    public void testDeleteTable() {
        Table table = new Table(1, "Table 1", 4, "Occupied", 0.0);
        tableService.addTable(table);

        tableService.deleteTable(1);

        List<Table> tables = tableService.getAllTables();
        assertTrue(tables.isEmpty());
    }

    @Test
    public void testLoadTablesFromCSV() {
        // Assume the CSV has some entries; this test would be more comprehensive if you have an actual file to test with.
        List<Table> tables = tableService.getAllTables();
        assertFalse(tables.isEmpty());
    }
}
