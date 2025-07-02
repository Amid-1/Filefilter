package com.filefilter.detector;

public class TypeDetector {
    public static FileType detectType(String s) {
        if (s == null || s.trim().isEmpty()) return FileType.STRING; // Пусть пустая строка считается строкой
        try {
            Long.parseLong(s);
            return FileType.INTEGER;
        } catch (NumberFormatException e1) {
            try {
                Double.parseDouble(s);
                return FileType.FLOAT;
            } catch (NumberFormatException e2) {
                return FileType.STRING;
            }
        }
    }
}
