package com.filefilter.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

/**
 * Сервис для записи списка строк в файл.
 * <p>
 * Если список {@code lines} пуст, файл не создаётся и операция пропускается.
 * В случае ошибок записи выбрасывается {@link java.io.UncheckedIOException}.
 */
public class FileWriterService {
    private static final Logger log = LoggerFactory.getLogger(FileWriterService.class);

    /**
     * Записывает список строк в указанный файл.
     * Если файл существует и append = false, он будет перезаписан.
     * Если файл существует и append = true, строки будут добавлены в конец файла.
     * <p>
     * Если {@code lines} пуст, файл не создаётся.
     * <p>
     * В случае ошибки записи выбрасывает {@link java.io.UncheckedIOException}.
     *
     * @param lines   список строк для записи
     * @param filePath путь к файлу
     * @param append  если true — дописывать в конец файла, иначе перезаписывать
     * @throws java.io.UncheckedIOException при ошибке доступа к файлу (например, файл занят, нет прав на запись)
     */
    public static void writeToFile(List<String> lines, String filePath, boolean append) {
        if (lines.isEmpty()) {
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, append))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (FileNotFoundException e) {
            log.error("Файл {} занят другим процессом или недоступен для записи: {}", filePath, e.getMessage());
            System.err.println("Файл " + filePath + " занят другим процессом или недоступен для записи.");
            throw new UncheckedIOException("Файл занят другим процессом или недоступен для записи: " + filePath, e);
        } catch (IOException e) {
            log.error("Не удалось записать {} строк в файл {}: {}", lines.size(), filePath, e.getMessage());
            System.err.println("Не удалось записать в файл " + filePath + ": " + e.getMessage());
            throw new UncheckedIOException(e);
        }
    }
}