package com.filefilter.cli;

import java.util.ArrayList;
import java.util.List;

public class ArgsParser {
    private final boolean append;
    private final boolean shortStats;
    private final String prefix;
    private final String outputDir;
    private final List<String> inputFiles;

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

    public boolean isAppend() {
        return append;
    }

    public boolean isShortStats() {
        return shortStats;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public List<String> getInputFiles() {
        return inputFiles;
    }
}
