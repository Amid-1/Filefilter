package com.filefilter;

import com.filefilter.statistics.StringStatistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StringStatisticsTest {
    private StringStatistics stats;

    @BeforeEach
    void setUp() {
        stats = new StringStatistics();
    }

    @Test
    void emptyStats() {
        assertDoesNotThrow(stats::printShort);
        assertDoesNotThrow(stats::printFull);
    }

    @Test
    void singleValue() {
        stats.addValue("hello");
        assertEquals(1, stats.getCount());
        assertEquals(5, stats.getMinLength());
        assertEquals(5, stats.getMaxLength());
    }

    @Test
    void multipleValues() {
        stats.addValue("a");
        stats.addValue("abcd");
        stats.addValue("xyz");
        assertEquals(3, stats.getCount());
        assertEquals(1, stats.getMinLength());
        assertEquals(4, stats.getMaxLength());
    }
}
