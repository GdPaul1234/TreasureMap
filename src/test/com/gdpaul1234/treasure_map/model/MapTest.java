package com.gdpaul1234.treasure_map.model;

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
        map.addField(new Adventurer(1, 1, map, "Indiana", "S", "AADADA"));
        map.addField(new Adventurer(2, 0, map, "Lara", "S", "AADADAGGA"));
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

        var a = map.getFieldAt(2, 0);
        assertInstanceOf(Adventurer.class, a);
        assertEquals("Lara", ((Adventurer) a).getName());
    }
}