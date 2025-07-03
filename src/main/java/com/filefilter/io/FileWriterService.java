package com.filefilter.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

public class FileWriterService {
    private static final Logger log = LoggerFactory.getLogger(FileWriterService.class);

    public static void writeToFile(List<String> lines, String filePath, boolean append) {
        if (lines.isEmpty()) {
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, append))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            log.error("Не удалось записать {} строк в файл {}: {}", lines.size(), filePath, e.getMessage());
            throw new UncheckedIOException(e);
        }
    }
}