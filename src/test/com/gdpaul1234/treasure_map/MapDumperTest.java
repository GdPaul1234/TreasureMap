package com.gdpaul1234.treasure_map;

import com.gdpaul1234.treasure_map.model.Adventurer;
import com.gdpaul1234.treasure_map.model.Map;
import com.gdpaul1234.treasure_map.model.Mountain;
import com.gdpaul1234.treasure_map.model.Treasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class MapDumperTest {
    private Map map;

    @BeforeEach
    void setUp() {
        map = new Map(3, 4);
        map.addField(new Mountain(1, 0));
        map.addField(new Mountain(2, 1));
        map.addField(new Treasure(0, 3, 2));
        map.addField(new Treasure(1, 3, 3));
    }

    @Test
    void dump_ShouldDumpSimulationResult() {
        var moves = "AADADAGGA";
        var adventurer = new Adventurer(1, 1, map, "Lara", "S", moves);
        map.addField(adventurer);

        IntStream.range(0, moves.length()).forEach(i -> adventurer.move());

        var expectedResult = """
                C - 3 - 4
                M - 1 - 0
                M - 2 - 1
                # {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors
                restants}
                T - 1 - 3 - 2
                # {A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe
                vertical} - {Orientation} - {Nb. trésors ramassés}
                A - Lara - 0 - 3 - S - 3
                """;

        var dumpedResult = new MapDumper(map).dump();
        assertEquals(expectedResult, dumpedResult);
    }
}