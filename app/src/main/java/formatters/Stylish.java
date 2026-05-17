package formatters;

import java.util.Map;
import java.util.StringJoiner;

public class Stylish {
    private StringJoiner formattedRow = new StringJoiner("\n");

    public String generate(Map<String, Object[]> differenceObject) {
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

        return formattedRow.toString();
    }
}
