package com.filefilter.statistics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringStatistics implements Statistics {
    private static final Logger log = LoggerFactory.getLogger(StringStatistics.class);

    private int count = 0;
    private int minLen = Integer.MAX_VALUE;
    private int maxLen = Integer.MIN_VALUE;

    @Override
    public void addValue(String value) {
        int len = value.length();
        if (len < minLen) minLen = len;
        if (len > maxLen) maxLen = len;
        count++;
    }

    @Override
    public void printShort() {
        log.info("Количество строк: {}", count);
    }

    @Override
    public void printFull() {
        log.info("Количество строк: {}", count);
        if (count > 0) {
            log.info("Минимальная длина строки: {}", minLen);
            log.info("Максимальная длина строки: {}", maxLen);
        } else {
            log.info("Нет данных для статистики строк");
        }
    }

    public int getCount() { return count; }
    public int getMinLength() { return count > 0 ? minLen : 0; }
    public int getMaxLength() { return count > 0 ? maxLen : 0; }
}

