package com.filefilter;

import com.filefilter.cli.ArgsParser;
import com.filefilter.exeption.AllFilesFailedException;
import com.filefilter.service.FilterService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ArgsParser ap = new ArgsParser(args);

        // Проверяем, что были входные файлы
        if (ap.getInputFiles().isEmpty()) {
            log.error("Не указаны входные файлы!");
            System.exit(1);
        }

        // Обработка опции -o: создание выходной папки, если её нет
        File outDir = new File(ap.getOutputDir());
        if (!outDir.exists() && !outDir.mkdirs()) {
            log.error("Не удалось создать директорию {}", ap.getOutputDir());
            System.exit(1);
        }

        FilterService svc = new FilterService(
                ap.isAppend(),
                ap.getPrefix(),
                outDir,
                ap.isShortStats()
        );

        try {
            svc.processFiles(ap.getInputFiles());
        } catch (AllFilesFailedException e) {
            log.error("Не удалось обработать ни один файл", e);
            System.exit(2);
        }

        log.info("Обработка завершена успешно.");
    }
}



