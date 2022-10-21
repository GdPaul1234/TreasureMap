package com.gdpaul1234.treasure_map.util;

import com.gdpaul1234.treasure_map.model.Map;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserHelper {
    public static Matcher doParse(String regex, String string, int lineNumber) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(String.format("Malformed line at line %d", lineNumber));
        }

        return matcher;
    }

    public static void throwExceptionIfPositionNotValid(int x, int y, int lineNumber, Map map) {
        var xValid = x >= 0 && x < map.getWidth();
        var yValid = y >= 0 && y < map.getHeight();

        if (!xValid || !yValid) {
            throw new IllegalArgumentException(String.format("Position out of bounds at line %d", lineNumber));
        }
    }
}
