package org.example.restaurant.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("File Utility Tests")
class FileUtilTest {

    private static final String TEST_FILE_PATH = "testfile.txt";
    private static final String TEST_CONTENT = "This is a test content.";

    @BeforeEach
    void setUp() throws IOException {
        // Create a test file with initial content
        Files.write(Paths.get(TEST_FILE_PATH), TEST_CONTENT.getBytes());
    }

    @AfterEach
    void tearDown() throws IOException {
        // Delete the test file after each test
        Files.deleteIfExists(Paths.get(TEST_FILE_PATH));
    }

    @Test
    void saveToFile() throws IOException {
        // New content to write
        String newContent = "New content to write to the file.";

        // Use FileUtil to write content to the file
        FileUtil.saveToFile(TEST_FILE_PATH, newContent);

        // Read the file content to verify it matches the new content
        String fileContent = new String(Files.readAllBytes(Paths.get(TEST_FILE_PATH)));

        // Verify the file content is as expected
        assertEquals(newContent, fileContent);
    }

    //@Test
//    void testReadFromFile() throws IOException {
//        // Use FileUtil to read content from the file
//        List<String> content = FileUtil.loadFromFile(TEST_FILE_PATH);
//
//        // Verify that the content read matches the initial content
//        assertNotNull(content);
//        assertEquals(1, content.size()); // The initial content is a single line
//        assertEquals(TEST_CONTENT, content.get(0));
//    }

    @Test
    void testFileNotFound() {
        // Use FileUtil to read from a non-existent file
        String nonExistentFilePath = "nonexistentfile.txt";

        Exception exception = assertThrows(IOException.class, () -> {
            FileUtil.loadFromFile(nonExistentFilePath);
        });

        // Verify that the exception message is correct
        String expectedMessage = "File not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
