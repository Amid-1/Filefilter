package com.filefilter.detector;

public class TypeDetector {
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
