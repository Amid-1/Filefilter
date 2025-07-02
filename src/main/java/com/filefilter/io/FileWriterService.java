package com.filefilter.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileWriterService {
    public static void writeToFile(List<String> lines, String filePath, boolean append) {

        if (lines == null || lines.isEmpty()) {
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, append))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл " + filePath + ": " + e.getMessage());
        }
    }
}