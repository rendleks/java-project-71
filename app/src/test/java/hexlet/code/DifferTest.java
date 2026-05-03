package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        var path = getFixturePath(fileName);
        return  Files.readString(path).trim();
    }

    @Test
    public void testFlatList() throws Exception {

        var pathFile1 = getFixturePath("file1.json").toString();
        var pathFile2 = getFixturePath("file2.json").toString();

        var diff = Differ.generate(pathFile1, pathFile2, "stylish");

        var result = readFixture("resultStylish.txt");

        assertEquals(result, diff);
    }
}
