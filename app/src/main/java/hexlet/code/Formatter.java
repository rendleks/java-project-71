package hexlet.code;

import formatters.Plain;
import formatters.Stylish;

import java.util.Map;


public class Formatter {
    public static String format(String format, Map<String, Object[]> differenceObject) {

        switch (format) {
            case "stylish":
                return new Stylish().generate(differenceObject);
            case "plain":
                return new Plain().generate(differenceObject);
            default:
                throw new UnsupportedOperationException(
                        "Unsupported file format: " + format
                                + ". Supported: stylish, plain"
                );
        }
    }


}
