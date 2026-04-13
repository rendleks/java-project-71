package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import hexlet.code.Gendiff.*;


@Command(name = "gendiff", mixinStandardHelpOptions = true,
description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "stylish";

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    String filepath1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    String filepath2;

    @Override
    public void run() {
        String valueFile1 = null;
        try {
            valueFile1 = Gendiff.readFile(filepath1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String valueFile2 = null;
        try {
            valueFile2 = Gendiff.readFile(filepath2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println(valueFile1);
        System.out.println(valueFile2);

    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.out.println(args);
        System.exit(exitCode);
    }
}
