package com.filefilter.statistics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberStatistics implements Statistics {
    private static final Logger log = LoggerFactory.getLogger(NumberStatistics.class);

    private int count = 0;
    private double min = Double.POSITIVE_INFINITY;
    private double max = Double.NEGATIVE_INFINITY;
    private double sum = 0.0;

    @Override
    public void addValue(String value) {
        try {
            double number = Double.parseDouble(value);
            min = Math.min(min, number);
            max = Math.max(max, number);
            sum += number;
            count++;
        } catch (NumberFormatException ignored) {
        }
    }

    @Override
    public void printShort() {
        log.info("Количество чисел: {}", count);
    }

    @Override
    public void printFull() {
        log.info("Количество чисел: {}", count);
        if (count > 0) {
            log.info("Минимум: {}", min);
            log.info("Максимум: {}", max);
            log.info("Сумма: {}", sum);
            log.info("Среднее: {}", sum / count);
        } else {
            log.info("Нет данных для статистики чисел");
        }
    }

    public int getCount() { return count; }
    public double getMin()    { return count > 0 ? min : 0; }
    public double getMax()    { return count > 0 ? max : 0; }
    public double getSum()    { return sum; }
    public double getAverage(){ return count > 0 ? sum / count : 0; }
}
