package org.example.restaurant.util;

/*
Utility class for file handling (loading and saving data).
 */

import java.io.*;

public class FileUtil {

    public static void saveToFile(String filePath, String content) throws IOException {
        // TODO: Implement method to save content to file
        try{
            File file = new File(filePath);
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            fw.write(content);
            fw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String loadFromFile(String filePath) throws IOException {
        // TODO: Implement method to load content from file
        //return null;
        try{
            File file = new File(filePath);
            if(!file.exists()){
                file.createNewFile();
            }
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            return br.readLine();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
