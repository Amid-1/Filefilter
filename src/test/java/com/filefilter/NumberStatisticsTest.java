package com.filefilter;

import com.filefilter.statistics.NumberStatistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class NumberStatisticsTest {
    private NumberStatistics stats;

    @BeforeEach
    void setUp() {
        stats = new NumberStatistics();
    }

    @Test
    void emptyStats() {
        assertDoesNotThrow(stats::printShort);
        assertDoesNotThrow(stats::printFull);
    }

    @Test
    void singleValue() {
        stats.addValue("10");
        assertEquals(1, stats.getCount());
        assertEquals(10.0, stats.getMin());
        assertEquals(10.0, stats.getMax());
        assertEquals(10.0, stats.getSum());
        assertEquals(10.0, stats.getAverage());
    }

    @Test
    void multipleValues() {
        stats.addValue("1");
        stats.addValue("5");
        stats.addValue("3");
        assertEquals(3, stats.getCount());
        assertEquals(1.0, stats.getMin());
        assertEquals(5.0, stats.getMax());
        assertEquals(9.0, stats.getSum());
        assertEquals(3.0, stats.getAverage(), 1e-9);
    }

    @Test
    void invalidValueIsIgnored() {
        stats.addValue("abc");
        assertEquals(0, stats.getCount());
    }
}
