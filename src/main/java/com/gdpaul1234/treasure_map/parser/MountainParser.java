package com.gdpaul1234.treasure_map.parser;

import com.gdpaul1234.treasure_map.model.Map;
import com.gdpaul1234.treasure_map.model.Mountain;

import java.util.regex.Matcher;

import static com.gdpaul1234.treasure_map.util.ParserHelper.doParse;
import static com.gdpaul1234.treasure_map.util.ParserHelper.throwExceptionIfPositionNotValid;

public class MountainParser implements LineParser<Mountain> {
    private final Map map;
    private final String line;
    private final int lineNumber;

    public MountainParser(Map map, String line, int lineNumber) {
        this.map = map;
        this.line = line;
        this.lineNumber = lineNumber;
    }

    @Override
    public Mountain parseLine() {
        Matcher matcher = doParse("M - (\\d+) - (\\d+)", this.line, this.lineNumber);
        var x = Integer.parseInt(matcher.group(1));
        var y = Integer.parseInt(matcher.group(2));

        throwExceptionIfPositionNotValid(x, y, this.lineNumber, this.map);

        return new Mountain(x, y);
    }
}
