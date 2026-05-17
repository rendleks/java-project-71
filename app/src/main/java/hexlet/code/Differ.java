package hexlet.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;


public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws IOException {

        // Считываем файлы
        // Парсинг данных
        // Находи разницу
        // Передать разницу форматеру
        // Выводим результат в консоль

        var parsingFile1 = new Parser().parsing(filepath1); // парсинг данных
        var parsingFile2 = new Parser().parsing(filepath2);
        var buildDifference = difference(parsingFile1, parsingFile2); // построение разницы
        var formatterString = formatter(format, buildDifference); // вывод разницы в зависимости от формата

        return formatterString;
    }

    private static Map<String, Object[]> difference(Map<String, Object> file1, Map<String, Object> file2) {

        var keys1 = new ArrayList<>(file1.entrySet().stream().map(a -> a.getKey()).toList());
        var keys2 = new ArrayList<>(file2.entrySet().stream().map(a -> a.getKey()).toList());

        var unionKeys = new ArrayList<>(keys1);
        unionKeys.addAll(keys2);

        var result = new LinkedHashMap<String, Object[]>();
        var uniqueKeysSet = new HashSet<>(unionKeys);
        var uniqueKeys = uniqueKeysSet.stream().sorted().toArray();

        for (var key : uniqueKeys) {

            var val1 = file1.getOrDefault(key, null);
            var val2 = file2.getOrDefault(key, null);

            if (keys1.contains(key) && val1 == null) {
                val1 = "null";
            }

            if (keys2.contains(key) && val2 == null) {
                val2 = "null";
            }

            if ((val1 != null && val2 != null) && val1.equals(val2)) {
                result.put(key.toString(), new Object[]{"unchange", val1});
            }

            if ((val1 != null && val2 != null) && !val1.equals(val2)) {
                result.put(key.toString(), new Object[]{"update", val1, val2});
            }
            if (val1 == null && val2 != null) {
                result.put(key.toString(), new Object[]{"add", val2});
            }
            if (val1 != null && val2 == null) {
                result.put(key.toString(), new Object[]{"remove", val1});
            }
        }

        return result;
    }

    private static String formatter(String format, Map<String, Object[]> differenceObject) {

        var formattedRow = new StringJoiner("\n");

        switch (format) {
            case "stylish":
                formattedRow.add("{");
                for (Map.Entry<String, Object[]> entry : differenceObject.entrySet()) {
                    String key = entry.getKey();
                    Object[] value = entry.getValue();

                    switch (value[0].toString()) {
                        case "update":
                            formattedRow.add(String.format("  - %s: %s", key, value[1].toString()));
                            formattedRow.add(String.format("  + %s: %s", key, value[2].toString()));
                            break;
                        case "add":
                            formattedRow.add(String.format("  + %s: %s", key, value[1].toString()));
                            break;
                        case "remove":
                            formattedRow.add(String.format("  - %s: %s", key, value[1].toString()));
                            break;
                        default:
                            formattedRow.add(String.format("    %s: %s", key, value[1].toString()));
                            break;
                    }
                }
                formattedRow.add("}");
                break;
            case "plain":
                for (Map.Entry<String, Object[]> entry : differenceObject.entrySet()) {
                    String key = entry.getKey();
                    Object[] value = entry.getValue();

                    switch (value[0].toString()) {
                        case "update":
                            var val1 = utilPlainFormat(value[1]);
                            var val2 = utilPlainFormat(value[2]);
                            formattedRow.add(String.format(
                                    "Property '%s' was updated. From %s to %s",
                                    key,
                                    val1,
                                    val2)
                            );
                            break;
                        case "add":
                            var valAdd = utilPlainFormat(value[1]);
                            formattedRow.add(String.format("Property '%s' was added with value: %s", key, valAdd));
                            break;
                        case "remove":
                            formattedRow.add(String.format("Property '%s' was removed", key));
                            break;
                        default:
                            break;
                    }
                }
                break;
            default:
                formattedRow.add("Unknown format!");
                break;
        }

        return formattedRow.toString();
    }

    private static String utilPlainFormat(Object value) {
        var typeValue = value.getClass().getSimpleName();

        if (value.equals("null")) {
            return "null";
        }

        switch (typeValue) {
            case "Boolean":
            case "Integer":
                return value.toString();
            case "LinkedHashMap":
            case "ArrayList":
                return "[complex value]";
            default:
                return "'" + value + "'";
        }
    }
}
