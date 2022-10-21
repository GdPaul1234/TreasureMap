package com.gdpaul1234.treasure_map.parser;

import com.gdpaul1234.treasure_map.model.Map;

import java.util.regex.Matcher;

import static com.gdpaul1234.treasure_map.util.ParserHelper.doParse;

public class MapParser implements LineParser<Map> {
    private final String line;
    private final int lineNumber;

    public MapParser(String line, int lineNumber) {
        this.line = line;
        this.lineNumber = lineNumber;
    }

    @Override
    public Map parseLine() {
        Matcher matcher = doParse("C - (\\d+) - (\\d+)", this.line, this.lineNumber);
        var width = Integer.parseInt(matcher.group(1));
        var height = Integer.parseInt(matcher.group(2));

        if (width < 0 || height < 0) {
            throw new IllegalArgumentException(String.format("Invalid width or height at line %d", this.lineNumber));
        }

        return new Map(width, height);
    }
}
