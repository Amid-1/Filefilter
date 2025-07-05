package com.filefilter.detector;

/**
 * Утилита для определения типа данных по строке.
 */
public class TypeDetector {

    /**
     * Определяет тип данных по содержимому строки.
     * <p>
     * Проверка выполняется в следующем порядке:
     * <ol>
     *   <li>Если строка пустая или состоит только из пробелов — считается {@link FileType#STRING}.</li>
     *   <li>Если строка успешно парсится как целое число (Long) — {@link FileType#INTEGER}.</li>
     *   <li>Если строка парсится как вещественное число (Double) — {@link FileType#FLOAT}.</li>
     *   <li>В остальных случаях — {@link FileType#STRING}.</li>
     * </ol>
     * Такой порядок обеспечивает приоритет целых над вещественными (например, "10" — это INTEGER, а не FLOAT).
     * <br>
     * Пустые строки и строки из одних пробелов считаются строками по смыслу задания.
     *
     * @param s исходная строка
     * @return определённый тип данных {@link FileType}
     */
    public static FileType detectType(String s) {
        String str = s.trim();
        if (str.isEmpty()) {
            return FileType.STRING;
        }
        try {
            Long.parseLong(str);
            return FileType.INTEGER;
        } catch (NumberFormatException e1) {
            try {
                Double.parseDouble(str);
                return FileType.FLOAT;
            } catch (NumberFormatException e2) {
                return FileType.STRING;
            }
        }
    }
}
