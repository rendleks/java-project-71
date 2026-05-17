package hexlet.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;


public class Differ {
    public static String generate(String filepath1, String filepath2, String format) throws IOException {

        var parsingFile1 = new Parser().parsing(filepath1); // парсинг данных
        var parsingFile2 = new Parser().parsing(filepath2);
        var buildDifference = difference(parsingFile1, parsingFile2); // построение разницы
        var formatterString = Formatter.format(format, buildDifference); // вывод разницы в зависимости от формата

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
}
