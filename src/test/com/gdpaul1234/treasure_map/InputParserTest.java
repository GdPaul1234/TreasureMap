package com.gdpaul1234.treasure_map;

import com.gdpaul1234.treasure_map.model.Adventurer;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InputParserTest {

    @Test
    void parse_ShouldReturnParsedMap() {
        var inputStream = Stream.of(
                "C - 3 - 4",
                "M - 1 - 0",
                "M - 2 - 1",
                "T - 0 - 3 - 2",
                "T - 1 - 3 - 3",
                "A - Lara - 1 - 1 - S - AADADAGGA"
        );

        var parsedMap = new InputParser(inputStream).parse();

        var expectedResult = """
                C - 3 - 4
                M - 1 - 0
                M - 2 - 1
                # {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors
                restants}
                T - 0 - 3 - 2
                T - 1 - 3 - 3
                # {A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe
                vertical} - {Orientation} - {Nb. trésors ramassés}
                A - Lara - 1 - 1 - S - 0
                """;
        assertEquals(expectedResult, new MapDumper(parsedMap).dump());

        var adventurer = (Adventurer) parsedMap.getFieldAt(1, 1);
        assertEquals("AADADAGGA",
                adventurer.getMoves().stream()
                        .map(Enum::toString)
                        .collect(Collectors.joining()));
    }

    @Test
    void parse_ShouldIgnoreComment() {
        var inputStream = Stream.of(
                "# Un commentaire",
                "C - 3 - 4",
                "# Un autre commentaire");

        var parsedMap = new InputParser(inputStream).parse();

        assertEquals(3, parsedMap.getWidth());
        assertEquals(4, parsedMap.getHeight());
    }

    @Test
    void parse_ShouldIgnoreEmptyLine() {
        var inputStream = Stream.of(
                "",
                "C - 3 - 4",
                "");

        var parsedMap = new InputParser(inputStream).parse();

        assertEquals(3, parsedMap.getWidth());
        assertEquals(4, parsedMap.getHeight());
    }

    @Test
    void parse_ShouldThrowExceptionIfMalformedLine() {
        var inputStream = Stream.of(
                "C - Aie - 4 - Ouch"
        );

        assertThrows(IllegalArgumentException.class,
                () -> new InputParser(inputStream).parse());
    }
}