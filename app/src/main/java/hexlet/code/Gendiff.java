package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
}
