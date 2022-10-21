package com.gdpaul1234.treasure_map;

import com.gdpaul1234.treasure_map.model.Map;
import com.gdpaul1234.treasure_map.parser.AdventurerParser;
import com.gdpaul1234.treasure_map.parser.MapParser;
import com.gdpaul1234.treasure_map.parser.MountainParser;
import com.gdpaul1234.treasure_map.parser.TreasureParser;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class InputParser {
    private final Stream<String> stream;
    private Map map;

    public InputParser(Stream<String> stream) {
        this.stream = stream;
    }

    public Map parse() {
        var lineNumber = new AtomicInteger();

        this.stream.forEach(line -> {
            lineNumber.getAndIncrement();

            switch (line.charAt(0)) {
                case 'C' -> this.map = new MapParser(line, lineNumber.get()).parseLine();
                case 'M' -> this.map.addField(new MountainParser(this.map, line, lineNumber.get()).parseLine());
                case 'T' -> this.map.addField(new TreasureParser(this.map, line, lineNumber.get()).parseLine());
                case 'A' -> this.map.addField(new AdventurerParser(this.map, line, lineNumber.get()).parseLine());
                default -> throw new IllegalArgumentException(String.format("Error when parsing line %d", lineNumber.get()));
            }
        });

        return map;
    }
}
