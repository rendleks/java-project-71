package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;


@Command(name = "gendiff", mixinStandardHelpOptions = true,
description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "stylish";

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    File filepath1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    File filepath2;

    @Override
    public void run() {
        // This business logic of the command goes here ...
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.out.println(args);
        System.exit(exitCode);
    }
}
