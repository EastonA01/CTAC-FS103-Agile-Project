package org.example.restaurant.util;

/*
Utility class for file handling (loading and saving data).
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    /**
    Writes data content to a CSV file
     @param filePath The path of the file location
     @param data A List of Strings, written line-by-line in the buffered writer
     */
    public static void writeToCSV(String filePath, List<String[]> data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] line : data) {
                writer.write(String.join(",", line));
                writer.newLine();
            }
        }
    }

    /**
     Reads data content to a CSV file
     @param filePath The path of the file location
     @return List of Iterated Strings, split using a comma
     */
    public static List<String[]> readFromCSV(String filePath) throws IOException {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineData = line.split(",");
                data.add(lineData);
            }
        }
        return data;
    }

}
