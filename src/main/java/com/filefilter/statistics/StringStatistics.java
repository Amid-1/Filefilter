package com.filefilter.statistics;

public class StringStatistics implements Statistics {
    private int count = 0;
    private int minLen = Integer.MAX_VALUE;
    private int maxLen = Integer.MIN_VALUE;

    // Добавьте вот эти геттеры:
    public int getCount() {
        return count;
    }

    public int getMinLength() {
        return count > 0 ? minLen : 0;
    }

    public int getMaxLength() {
        return count > 0 ? maxLen : 0;
    }

    @Override
    public void addValue(String value) {
        int len = value.length();
        if (len < minLen) minLen = len;
        if (len > maxLen) maxLen = len;
        count++;
    }

    @Override
    public void printShort() {
        System.out.println("Количество: " + count);
    }

    @Override
    public void printFull() {
        System.out.println("Количество: " + count);
        if (count > 0) {
            System.out.println("Минимальная длина строки: " + minLen);
            System.out.println("Максимальная длина строки: " + maxLen);
        }
    }
}

