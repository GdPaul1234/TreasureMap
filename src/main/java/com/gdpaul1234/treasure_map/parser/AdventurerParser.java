package com.gdpaul1234.treasure_map.parser;

import com.gdpaul1234.treasure_map.model.Adventurer;
import com.gdpaul1234.treasure_map.model.Map;

import java.util.regex.Matcher;

import static com.gdpaul1234.treasure_map.util.ParserHelper.doParse;
import static com.gdpaul1234.treasure_map.util.ParserHelper.throwExceptionIfPositionNotValid;

public class AdventurerParser implements LineParser<Adventurer> {
    private final Map map;
    private final String line;
    private final int lineNumber;

    public AdventurerParser(Map map, String line, int lineNumber) {
        this.map = map;
        this.line = line;
        this.lineNumber = lineNumber;
    }

    @Override
    public Adventurer parseLine() {
        Matcher matcher = doParse(
                "A - (\\w+) - (\\d+) - (\\d+) - ([NSOE]) - ([AGD]+)",
                this.line, this.lineNumber);

        var name = matcher.group(1);
        var x = Integer.parseInt(matcher.group(2));
        var y = Integer.parseInt(matcher.group(3));
        var orientation = matcher.group(4);
        var moves = matcher.group(5);

        throwExceptionIfPositionNotValid(x, y, this.lineNumber, this.map);

        return new Adventurer(x, y, map, name, orientation, moves);
    }
}
