package com.filefilter.statistics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Реализация статистики для строковых данных.
 * <p>
 * Сохраняет следующие метрики:
 * <ul>
 *     <li>Количество строк</li>
 *     <li>Минимальную и максимальную длину строки</li>
 * </ul>
 * <p>
 * В случае отсутствия данных (count == 0):
 * <ul>
 *     <li>Методы getMinLength(), getMaxLength() возвращают 0</li>
 *     <li>printFull() выводит сообщение о том, что данных нет</li>
 * </ul>
 */
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

