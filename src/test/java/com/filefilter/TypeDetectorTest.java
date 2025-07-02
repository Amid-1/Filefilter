package com.filefilter;

import com.filefilter.detector.FileType;
import com.filefilter.detector.TypeDetector;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TypeDetectorTest {

    @Test
    void detectInteger() {
        assertEquals(FileType.INTEGER, TypeDetector.detectType("123"));
        assertEquals(FileType.INTEGER, TypeDetector.detectType("-42"));
    }

    @Test
    void detectFloat() {
        // точка
        assertEquals(FileType.FLOAT, TypeDetector.detectType("3.14"));
        // в экспоненциальной форме
        assertEquals(FileType.FLOAT, TypeDetector.detectType("1e-3"));
    }

    @Test
    void detectString() {
        assertEquals(FileType.STRING, TypeDetector.detectType("hello"));
        assertEquals(FileType.STRING, TypeDetector.detectType("123abc"));
    }

    @Test
    void detectEmptyOrWhitespace() {
        // пустая или пробельная строка считаются STRING
        assertEquals(FileType.STRING, TypeDetector.detectType(""));
        assertEquals(FileType.STRING, TypeDetector.detectType("   "));
    }
}
