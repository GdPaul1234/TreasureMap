package com.gdpaul1234.treasure_map.util;

import com.gdpaul1234.treasure_map.model.Map;
import com.gdpaul1234.treasure_map.model.Mountain;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

class ParserHelperTest {
    @Nested
    class describeDoParse {
        @Nested
        class contextMatches {
            @Test
            void doParse_ShouldReturnMatcher() {
                var regex = "M - (\\d+) - (\\d+)";
                var string = "M - 3 - 4";

                var matcher = ParserHelper.doParse(regex, string, 0);

                assertInstanceOf(Matcher.class, matcher);
                assertTrue(matcher.matches());
            }

            @Test
            void doParse_ShouldParseField() {
                var regex = "M - (\\d+) - (\\d+)";
                var string = "M - 3 - 4";

                var matcher = ParserHelper.doParse(regex, string, 0);

                assertEquals("3", matcher.group(1));
                assertEquals("4", matcher.group(2));
            }
        }

        @Nested
        class contextNoMatches {
            @Test
            void doParse_ShouldThrowException() {
                var regex = "M - (\\d+) - (\\d+)";
                var string = "M - Aie! - 4";

                assertThrows(IllegalArgumentException.class, () -> ParserHelper.doParse(regex, string, 0));
            }
        }
    }

    @Nested
    class describeThrowExceptionIfPositionNotValid {
        @Test
        void whenPositionOutOfBound_throwExceptionIfPositionNotValid_ShouldThrowException() {
            var map = new Map(3, 4);

            assertThrows(IllegalArgumentException.class,
                    () -> ParserHelper.throwExceptionIfPositionNotValid(3, 1, 0, map));
            assertThrows(IllegalArgumentException.class,
                    () -> ParserHelper.throwExceptionIfPositionNotValid(1, -1, 0, map));
        }

        @Test
        void whenPositionIsInBound_throwExceptionIfPositionNotValid_ShouldNotThrowException() {
            var map = new Map(3, 4);

            assertDoesNotThrow(
                    () -> ParserHelper.throwExceptionIfPositionNotValid(1, 1, 0, map));
        }
    }

    @Nested
    class describeThrowExceptionIfFieldIsNotEmpty {
        @Test
        void whenFieldIsNotEmpty_throwExceptionIfFieldIsNotEmpty_ShouldThrowException() {
            var map = new Map(3, 4);
            map.addField(new Mountain(1, 1));

            assertThrows(IllegalArgumentException.class,
                    () -> ParserHelper.throwExceptionIfFieldIsNotEmpty(1, 1, 0, map));
        }

        @Test
        void whenFieldIsEmpty_throwExceptionIfFieldIsNotEmpty_ShouldNotThrowException() {
            var map = new Map(3, 4);
            map.addField(new Mountain(1, 1));

            assertDoesNotThrow(
                    () -> ParserHelper.throwExceptionIfFieldIsNotEmpty(1, 0, 0, map));
        }
    }
}
