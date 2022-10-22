package com.gdpaul1234.treasure_map;

import com.gdpaul1234.treasure_map.model.Map;
import com.gdpaul1234.treasure_map.parser.AdventurerParser;
import com.gdpaul1234.treasure_map.parser.MapParser;
import com.gdpaul1234.treasure_map.parser.MountainParser;
import com.gdpaul1234.treasure_map.parser.TreasureParser;

import java.text.MessageFormat;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class InputParser {
    private final Stream<String> stream;
    private Map map;

    private static final Logger LOGGER = Logger.getLogger(InputParser.class.getName());

    public InputParser(Stream<String> stream) {
        this.stream = stream;
    }

    public Map parse() {
        var lineNumber = new AtomicInteger();

        this.stream.forEach(line -> {
            lineNumber.getAndIncrement();

            if (line.length() == 0) return;

            switch (line.charAt(0)) {
                case 'C' -> this.map = new MapParser(line, lineNumber.get()).parseLine();
                case 'M' -> this.map.addField(new MountainParser(this.map, line, lineNumber.get()).parseLine());
                case 'T' -> this.map.addField(new TreasureParser(this.map, line, lineNumber.get()).parseLine());
                case 'A' -> this.map.addField(new AdventurerParser(this.map, line, lineNumber.get()).parseLine());
                case '#' -> LOGGER.info(() -> MessageFormat.format("Comment found at line {0}", lineNumber.get()));
                default -> throw new IllegalArgumentException(String.format("Error when parsing line %d", lineNumber.get()));
            }
        });

        return map;
    }
}
