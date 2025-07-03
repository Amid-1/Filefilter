package com.filefilter;

import com.filefilter.cli.ArgsParser;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArgsParserTest {

    @Test
    void validArgs() {
        String[] args = {"-a", "-f", "-o", "outDir", "-p", "pre_", "file1.txt", "file2.txt"};
        ArgsParser ap = new ArgsParser(args);
        assertTrue(ap.isAppend());
        assertFalse(ap.isShortStats());  // флаг -f преобладает
        assertEquals("pre_", ap.getPrefix());
        assertEquals("outDir", ap.getOutputDir());
        assertEquals(List.of("file1.txt","file2.txt"), ap.getInputFiles());
    }

    @Test
    void unknownOptionThrows() {
        String[] args = {"-x", "file.txt"};
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> new ArgsParser(args)
        );
        assertTrue(ex.getMessage().contains("Неизвестная опция"));
    }
}