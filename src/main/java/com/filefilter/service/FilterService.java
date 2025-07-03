package com.filefilter.service;

import com.filefilter.detector.TypeDetector;
import com.filefilter.exception.AllFilesFailedException;
import com.filefilter.statistics.NumberStatistics;
import com.filefilter.statistics.Statistics;
import com.filefilter.statistics.StringStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

import static com.filefilter.io.FileWriterService.writeToFile;

public class FilterService {
    private static final Logger log = LoggerFactory.getLogger(FilterService.class);

    private final boolean append;
    private final String prefix;
    private final File outDir;
    private final boolean shortStats;

    public FilterService(boolean append, String prefix, File outDir, boolean shortStats) {
        this.append = append;
        this.prefix = prefix;
        this.outDir = outDir;
        this.shortStats = shortStats;
    }

    public void processFiles(List<String> inputFiles) throws AllFilesFailedException {
        List<String> integers = new ArrayList<>();
        List<String> floats   = new ArrayList<>();
        List<String> strings  = new ArrayList<>();

        Statistics intStats   = new NumberStatistics();
        Statistics floatStats = new NumberStatistics();
        Statistics strStats   = new StringStatistics();

        boolean anySuccess = false;

        //  чтение входных файлов
        for (String filename : inputFiles) {
            log.info("Читаем файл {}", filename);
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                boolean thisFileHadData = false;
                while ((line = reader.readLine()) != null) {
                    if (line.trim().isEmpty()) continue;
                    thisFileHadData = true;
                    anySuccess = true;
                    switch (TypeDetector.detectType(line)) {
                        case INTEGER -> {
                            integers.add(line);
                            intStats.addValue(line);
                        }
                        case FLOAT -> {
                            floats.add(line);
                            floatStats.addValue(line);
                        }
                        case STRING -> {
                            strings.add(line);
                            strStats.addValue(line);
                        }
                    }
                }
                if (!thisFileHadData) {
                    log.warn("В файле {} не найдено ни одной непустой строки", filename);
                }
            } catch (IOException e) {
                log.warn("Не смогли прочитать файл {}", filename, e);
            }
        }

        if (!anySuccess) {
            throw new AllFilesFailedException("Ни один файл не прочитан или все файлы пусты");
        }

        //  Обёртка для записи, чтобы поймать любые UncheckedIOException
        try {
            writeCategory(integers, "integers.txt");
            writeCategory(floats,   "floats.txt");
            writeCategory(strings,  "strings.txt");
        } catch (UncheckedIOException e) {
            log.error("Ошибка при записи выходных файлов: {}", e.getMessage());
            System.exit(3);
        }

        //  вывод статистики
        printStats("целых чисел",       intStats);
        printStats("вещественных чисел", floatStats);
        printStats("строк",              strStats);
    }

    private void writeCategory(List<String> data, String baseName) {
        if (data.isEmpty()) return;
        File outFile = new File(outDir, prefix + baseName);
        writeToFile(data, outFile.getAbsolutePath(), append);
        log.info("Записано {} элементов в {}", data.size(), outFile.getAbsolutePath());
    }

    private void printStats(String label, Statistics stats) {
        String mode = shortStats ? "Short-stat" : "Full-stat";
        String flag = shortStats ? "-s" : "-f";
        log.info("{} ({}) для {}:", mode, flag, label);
        if (shortStats) stats.printShort();
        else            stats.printFull();
    }
}