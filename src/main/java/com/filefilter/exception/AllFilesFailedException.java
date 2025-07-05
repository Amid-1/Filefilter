package com.filefilter.exception;

/**
 * Исключение выбрасывается в случае, если ни один из переданных входных файлов
 * не был успешно прочитан либо все они оказались пустыми
 * (не содержали ни одной непустой строки).
 */
public class AllFilesFailedException extends Exception {
    /**
     * Создает исключение с заданным сообщением, описывающим причину
     * невозможности обработки входных файлов.
     *
     * @param message подробное описание причины ошибки
     */
    public AllFilesFailedException(String message) {
        super(message);
    }
}
