package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "gendiff", mixinStandardHelpOptions = true,
description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {

    @Override
    public void run() {
        // This business logic of the command goes here ...
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
