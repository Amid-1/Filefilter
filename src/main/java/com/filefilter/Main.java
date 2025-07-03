package com.filefilter;

import com.filefilter.cli.ArgsParser;
import com.filefilter.exception.AllFilesFailedException;
import com.filefilter.service.FilterService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ArgsParser ap;
        try {
            ap = new ArgsParser(args);
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка в аргументах: " + e.getMessage());
            System.err.println("Использование: java -jar file-filter.jar [-a] [-s|-f] [-o <путь>] [-p <префикс>] <input-files...>");
            System.exit(1);
            return;
        }

        if (ap.getInputFiles().isEmpty()) {
            System.err.println("Ошибка: не указаны входные файлы");
            System.err.println("Использование: java -jar file-filter.jar [-a] [-s|-f] [-o <путь>] [-p <префикс>] <input-files...>");
            System.exit(1);
            return;
        }

        File outDir = new File(ap.getOutputDir());
        if (!outDir.exists() && !outDir.mkdirs()) {
            log.error("Не удалось создать директорию {}", ap.getOutputDir());
            System.exit(1);
            return;
        }

        if (!ap.isAppend()) {
            String[] baseNames = {"integers.txt", "floats.txt", "strings.txt"};
            for (String bn : baseNames) {
                File f = new File(outDir, ap.getPrefix() + bn);
                if (f.exists() && !f.delete()) {
                    System.err.println("Не удалось удалить старый файл: " + f.getAbsolutePath());
                }
            }
        }

        // Создание сервиса и запуск фильтрации
        FilterService svc = new FilterService(
                ap.isAppend(),
                ap.getPrefix(),
                outDir,
                ap.isShortStats()
        );

        try {
            svc.processFiles(ap.getInputFiles());
        } catch (AllFilesFailedException e) {
            log.error("Не удалось обработать ни один файл: {}", e.getMessage());
            System.exit(2);
            return;
        }

        log.info("Обработка завершена успешно.");
    }
}