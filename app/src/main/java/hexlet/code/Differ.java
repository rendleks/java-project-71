package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;


public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws IOException {

        // Считываем файлы
        // Парсинг данных
        // Находи разницу
        // Передать разницу форматеру
        // Выводим результат в консоль

        var readFile1 = readFile(filepath1); // чтение файлов
        var readFile2 = readFile(filepath2);

        var parsingFile1 = parsing(readFile1); // парсинг данных
        var parsingFile2 = parsing(readFile2);

        var buildDifference = difference(parsingFile1, parsingFile2); // построение разницы

        var formatterString = formatter(format, buildDifference); // вывод разницы в зависимости от формата

        return formatterString;
    }

    private static String readFile(String path) throws IOException {
        Path filePath = Paths.get(path).toAbsolutePath().normalize();
        return Files.readString(filePath).trim();
    }

    private static Map<String, Object> parsing(String fileReading) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(fileReading, new TypeReference<>() { });

        return map;
    }

    private static Map<String, Object> difference(Map<String, Object> file1, Map<String, Object> file2) {

        var keys1 = new ArrayList<>(file1.entrySet().stream().map(a -> a.getKey()).toList());
        var keys2 = new ArrayList<>(file2.entrySet().stream().map(a -> a.getKey()).toList());

        var unionKeys = new ArrayList<>(keys1);
        unionKeys.addAll(keys2);

        var result = new LinkedHashMap<String, Object>();
        var uniqueKeysSet = new HashSet<>(unionKeys);
        var uniqueKeys = uniqueKeysSet.stream().sorted().toArray();

        for (var key: uniqueKeys) {
            var val1 = file1.getOrDefault(key, null);
            var val2 = file2.getOrDefault(key, null);

            if ((val1 != null && val2 != null) && val1.equals(val2)) {
                var newKey = "  " + key;
                result.put(newKey, val1);
            }
            if ((val1 != null && val2 != null) && !val1.equals(val2)) {
                var newKey1 = "- " + key;
                result.put(newKey1, val1);
                var newKey2 = "+ " + key;
                result.put(newKey2, val1);
            }
            if (val1 == null && val2 != null) {
                var newKey = "+ " + key;
                result.put(newKey, val2);
            }
            if (val1 != null && val2 == null) {
                var newKey = "- " + key;
                result.put(newKey, val1);
            }
        }

        return result;
    }

    private static String formatter(String format, Map<String, Object> differenceObject) {

        var formattedRow = new StringBuilder();

        switch (format) {
            case "stylish":
                formattedRow.append("{\n");
                for (Map.Entry<String, Object> entry : differenceObject.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    formattedRow.append(String.format("  %s: %s\n", key, value));
                }
                formattedRow.append("}");
                break;
            default:
                formattedRow.append("Unknown format!");
                break;
        }

        return formattedRow.toString();
    }

}
