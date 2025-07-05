# FileFilter Utility

## Описание

Утилита на Java для фильтрации содержимого текстовых файлов.  
Разделяет вхождения целых чисел, вещественных чисел и строк в три отдельных файла и выводит по ним статистику.

---

## Требования

- **Java:** 21
- **Maven:** 3.14.0

---

## Сборка

```bash
mvn clean package
```
---

###  После сборки 

в каталоге target/ появится файл:
```
filefilter-1.0-SNAPSHOT.jar
```

---

### Использование

```bash
java -jar ".\target\filefilter-1.0-SNAPSHOT.jar" .\data1.txt .\data2.txt
```

### Параметры:

- `o <dir>` — каталог для выходных файлов (по умолчанию .).

- `p <prefix>` — префикс имён выходных файлов (по умолчанию пустой).

- `a` — дописывать в существующие файлы (append), вместо перезаписи.

- `s` — короткая статистика (только количество элементов).

- `f` — полная статистика (для чисел: количество, минимум, максимум, сумма, среднее; для строк: количество, минимальная и максимальная длина).

> ** По умолчанию (если не переданы ни -s, ни -f) выводится полная статистика. **

---

### Примеры
- 1) Полный режим (по умолчанию)

```bash
java -jar target/filefilter-1.0-SNAPSHOT.jar data1.txt data2.txt
```

- 2) Короткая статистика, вывод в папку results/

```bash
java -jar .\target\filefilter-1.0-SNAPSHOT.jar `
  -s `
  -o .\results `
  .\data1.txt `
  .\data2.txt
```

- 3) Префикс и режим append 

```bash
java -jar ".\target\filefilter-1.0-SNAPSHOT.jar" `
  -a `
  -f `
  -o ".\results" `
  -p result_ `
  ".\data1.txt" `
  ".\data2.txt"
```
- 4) Запуск с указанием директории для результатов и префиксом имён
```bash
java -jar .\target\filefilter-1.0-SNAPSHOT.jar -o .\results -p result_ .\data1.txt .\data2.txt
```

- 5) Без разбивки на несколько строк
```bash
java -jar .\target\filefilter-1.0-SNAPSHOT.jar -o .\results -p result_ .\data1.txt .\data2.txt
```

### Примеры сообщения об ошибках

- Ошибка в аргументах: После -o должен быть путь к папке!
     Использование: java -jar file-filter.jar [-a] [-s|-f] [-o <путь>] [-p <префикс>] <input-files...>
- Не удалось создать директорию results

- В файле data2.txt не найдено ни одной непустой строки

- Не удалось записать 5 строк в файл results/result_integers.txt: Файл занят другим процессом

- Нет прав на запись в директорию: /some/output/path