package com.gdpaul1234.treasure_map;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {
        var inputFilePath = Paths.get(args[0]);

        try(var lines = Files.lines(inputFilePath)) {
            // Parse input file
            var map = new InputParser(lines).parse();
            LOGGER.info(() -> MessageFormat.format("Parse file {0}", inputFilePath));

            // Simulate adventurers moves
            map.runSimulation();
            LOGGER.info(() -> "Simulate adventurers moves");

            // Dump simulation result
            var result = new MapDumper(map).dump();
            var resultFilePath = inputFilePath.getParent()
                            .resolve(Paths.get( inputFilePath.getFileName() + ".result"));

            Files.writeString(resultFilePath, result);
            LOGGER.info(() -> MessageFormat.format("Dump result to {0}", resultFilePath));
        }
    }
}
