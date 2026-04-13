package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

    public static Json jsonToMap(String filename) {
        final ObjectMapper mapper = new ObjectMapper();

        Json myValue = mapper.readValue(readFile(filename), Json.class);

        return myValue;

    }
}
