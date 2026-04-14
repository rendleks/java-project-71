package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import hexlet.code.Json;



public class Gendiff {
    public static Path getFilePath(String filename) {
        Path path = Paths.get(filename);
        Path absolutePath = path.toAbsolutePath();

        return absolutePath.normalize();
    }

    public static String readFile(String filename) throws Exception {
        var path = getFilePath(filename);
        return Files.readString(path).trim();
    }

    public static Map<String, Object> jsonToMap(String filename) throws Exception {
        final ObjectMapper mapper = new ObjectMapper();

        String file = readFile(filename);

        Map<String, Object> map = mapper.readValue(file, new TypeReference<Map<String, Object>>() {
        });

        return map;
    }
}
