package com.filefilter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.filefilter.io.FileWriterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class FileWriterServiceTest {

    @TempDir
    Path tempDir;

    @Test
    void writeAndAppend(@TempDir Path temp) throws IOException {
        Path file = temp.resolve("out.txt");

        // первый запуск — перезапись
        FileWriterService.writeToFile(List.of("one", "two"), file.toString(), false);
        List<String> lines1 = Files.readAllLines(file);
        assertEquals(List.of("one", "two"), lines1);

        // второй запуск — append
        FileWriterService.writeToFile(List.of("three"), file.toString(), true);
        List<String> lines2 = Files.readAllLines(file);
        assertEquals(List.of("one", "two", "three"), lines2);
    }

    @Test
    void writeEmptyListCreatesNothing(@TempDir Path temp) {
        Path file = temp.resolve("nothing.txt");
        FileWriterService.writeToFile(List.of(), file.toString(), false);
        assertFalse(Files.exists(file), "Файл не должен быть создан при пустом списке");
    }
}

