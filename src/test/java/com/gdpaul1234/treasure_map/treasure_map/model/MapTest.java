package com.gdpaul1234.treasure_map.treasure_map.model;

import com.gdpaul1234.treasure_map.MapDumper;
import com.gdpaul1234.treasure_map.model.Adventurer;
import com.gdpaul1234.treasure_map.model.Map;
import com.gdpaul1234.treasure_map.model.Mountain;
import com.gdpaul1234.treasure_map.model.Treasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class MapTest {
    private Map map;

    @BeforeEach
    void init() {
        map = new Map(3, 4);
        map.addField(new Mountain(1, 1));
        map.addField(new Mountain(2, 2));
        map.addField(new Treasure(0, 3, 2));
        map.addField(new Treasure(1, 3, 1));
        map.addField(new Adventurer(0, 2, map, "Indiana", "S", "AGAA"));
        map.addField(new Adventurer(1, 2, map, "Lara", "E", "DA"));
    }

    @Test
    void getFieldOf_ShouldFilterAdventurerInInsertionOrder() {
        var list = map.getFieldOf(Adventurer.class);

        assertEquals("Indiana", list.get(0).getName());
        assertEquals("Lara", list.get(1).getName());
    }

    @Test
    void getFieldAt_ShouldReturnFieldAtGivenPosition() {
        assertInstanceOf(Mountain.class, map.getFieldAt(2, 2));

        var t = map.getFieldAt(0, 3);
        assertInstanceOf(Treasure.class, t);
        assertEquals(2, ((Treasure) t).getNbTreasure());

        var a = map.getFieldAt(1, 2);
        assertInstanceOf(Adventurer.class, a);
        assertEquals("Lara", ((Adventurer) a).getName());
    }

    @Test
    void runSimulation_ShouldMoveAdventurers() {
        map.runSimulation();

        var expectedResult = """
                C - 3 - 4
                M - 1 - 1
                M - 2 - 2
                # {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors
                restants}
                T - 0 - 3 - 1
                # {A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe
                vertical} - {Orientation} - {Nb. trésors ramassés}
                A - Indiana - 2 - 3 - E - 1
                A - Lara - 1 - 3 - S - 1
                """;

        assertEquals(expectedResult, new MapDumper(map).dump());
    }
}