package ua.hotel_reservation.logs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NewLogFileGenerator {


    //generated file name and creates file with this name and returns file name
    public static String createNewFile(String type, Path folderPath){
        String fileName = "logs-"+type+"-"+System.nanoTime() + ".txt";
        Path filePath = folderPath.resolve(fileName);
        try {
            Files.createFile(filePath);
        } catch (IOException e){
            e.printStackTrace();
        }

        return filePath.toString();
    }
}
