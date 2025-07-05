package com.filefilter.cli;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для парсинга аргументов командной строки.
 * <p>
 * Поддерживаемые параметры:
 * <ul>
 *   <li><b>-a</b> — режим добавления в существующие файлы (append), по умолчанию перезапись</li>
 *   <li><b>-s</b> — выводить краткую статистику</li>
 *   <li><b>-f</b> — выводить полную статистику (приоритет над -s)</li>
 *   <li><b>-o &lt;dir&gt;</b> — каталог для выходных файлов (по умолчанию текущая директория)</li>
 *   <li><b>-p &lt;prefix&gt;</b> — префикс имён выходных файлов (по умолчанию пусто)</li>
 *   <li><b>input-files...</b> — список входных файлов для обработки</li>
 * </ul>
 * В случае некорректных параметров выбрасывает IllegalArgumentException с сообщением об ошибке.
 */
public class ArgsParser {
    private final boolean append;
    private final boolean shortStats;
    private final String prefix;
    private final String outputDir;
    private final List<String> inputFiles;

    /**
     * Парсит аргументы командной строки и инициализирует параметры запуска программы.
     *
     * @param args массив аргументов командной строки
     * @throws IllegalArgumentException если обнаружены некорректные или неизвестные параметры,
     *                                  либо отсутствует значение для -o или -p
     */
    public ArgsParser(String[] args) {
        boolean a = false, s = false, f = false;
        String p = "";
        String o = ".";
        List<String> inputs = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-a" -> a = true;
                case "-s" -> s = true;
                case "-f" -> f = true;
                case "-p" -> {
                    if (i + 1 < args.length) p = args[++i];
                    else throw new IllegalArgumentException("После -p должен быть префикс!");
                }
                case "-o" -> {
                    if (i + 1 < args.length) o = args[++i];
                    else throw new IllegalArgumentException("После -o должен быть путь к папке!");
                }
                default -> {
                    if (args[i].startsWith("-")) {
                        throw new IllegalArgumentException("Неизвестная опция: " + args[i]);
                    } else {
                        inputs.add(args[i]);
                    }
                }
            }
        }

        // если оба флага s и f указаны — считаем, что f (полная) побеждает
        boolean shortSt = s && !f;
        this.append = a;
        this.shortStats = shortSt;
        this.prefix = p;
        this.outputDir = o;
        this.inputFiles = inputs;
    }

    /**
     * @return true, если выбран режим добавления в файлы (append)
     */
    public boolean isAppend() {
        return append;
    }

    /**
     * @return true, если выбрана краткая статистика (-s); false — полная (-f)
     */
    public boolean isShortStats() {
        return shortStats;
    }

    /**
     * @return префикс имён выходных файлов
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * @return путь к каталогу для выходных файлов
     */
    public String getOutputDir() {
        return outputDir;
    }

    /**
     * @return список входных файлов
     */
    public List<String> getInputFiles() {
        return inputFiles;
    }
}