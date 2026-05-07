package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {

    private static String readFile(String path) throws IOException {
        Path filePath = Paths.get(path).toAbsolutePath().normalize();
        return Files.readString(filePath).trim();
    }

    public static Map<String, Object> parsing(String filePath) throws IOException {
        String fileExtension = Paths.get(filePath).getFileName().toString(); // имя файла с расширением
        var fileReading = readFile(filePath);

        if (fileExtension.endsWith("json")) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(fileReading, new TypeReference<>() { });
        }

        if (fileExtension.endsWith("yaml")) {
            ObjectMapper mapper = new YAMLMapper();
            return mapper.readValue(fileReading, new TypeReference<>() { });
        }

        throw new UnsupportedOperationException(
                "Unsupported file extension: " + fileExtension
                        + ". Supported: json, yaml, yml"
        );
    }
}
