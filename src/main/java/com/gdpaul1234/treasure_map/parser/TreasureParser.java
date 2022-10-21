package com.gdpaul1234.treasure_map.parser;

import com.gdpaul1234.treasure_map.model.Map;
import com.gdpaul1234.treasure_map.model.Treasure;

import java.util.regex.Matcher;

import static com.gdpaul1234.treasure_map.util.ParserHelper.*;

public class TreasureParser implements LineParser<Treasure> {
    private final Map map;
    private final String line;
    private final int lineNumber;

    public TreasureParser(Map map, String line, int lineNumber) {
        this.map = map;
        this.line = line;
        this.lineNumber = lineNumber;
    }

    @Override
    public Treasure parseLine() {
        Matcher matcher = doParse("T - (\\d+) - (\\d+) - (\\d+)", this.line, this.lineNumber);
        var x = Integer.parseInt(matcher.group(1));
        var y = Integer.parseInt(matcher.group(2));
        var nbTreasure = Integer.parseInt(matcher.group(3));

        throwExceptionIfPositionNotValid(x, y, this.lineNumber, this.map);
        throwExceptionIfFieldIsNotEmpty(x, y, this.lineNumber, this.map);

        if (nbTreasure < 0) {
            throw new IllegalArgumentException(String.format("nbTreasure is negative at line %d", this.lineNumber));
        }

        return new Treasure(x, y, nbTreasure);
    }
}
