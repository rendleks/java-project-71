package formatters;

import java.util.Map;
import java.util.StringJoiner;


public class Plain {
    private StringJoiner formattedRow = new StringJoiner("\n");

    public String generate(Map<String, Object[]> differenceObject) {
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
