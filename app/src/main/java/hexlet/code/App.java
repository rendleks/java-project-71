package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;


@Command(name = "gendiff", mixinStandardHelpOptions = true,
description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {

    @Option(names = {"-f", "--format"},
            description = "output format [default: stylish]",
            defaultValue = "stylish")
    private String format;

    @Parameters(index = "0",
            description = "path to first file")
    private File filepath1;

    @Parameters(index = "1",
            description = "path to second file")
    private File filepath2;

    @Override
    public void run() {
        // This business logic of the command goes here ...
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
