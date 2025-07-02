package com.filefilter.statistics;

public class NumberStatistics implements Statistics {
    public int getCount() {
        return count;
    }

    public double getMin() {
        return count > 0 ? min : 0;
    }

    public double getMax() {
        return count > 0 ? max : 0;
    }

    public double getSum() {
        return sum;
    }

    public double getAverage() {
        return count > 0 ? sum / count : 0;
    }

    private int count = 0;
    private double min = Double.POSITIVE_INFINITY;
    private double max = Double.NEGATIVE_INFINITY;
    private double sum = 0.0;

    @Override
    public void addValue(String value) {
        try {
            double number = Double.parseDouble(value);
            if (number < min) min = number;
            if (number > max) max = number;
            sum += number;
            count++;
        } catch (NumberFormatException e) {
            // игнорируем некорректные значения
        }
    }

    @Override
    public void printShort() {
        System.out.println("Количество: " + count);
    }

    @Override
    public void printFull() {
        System.out.println("Количество: " + count);
        if (count > 0) {
            System.out.println("Минимум: " + min);
            System.out.println("Максимум: " + max);
            System.out.println("Сумма: " + sum);
            System.out.println("Среднее: " + (sum / count));
        }
    }
}
