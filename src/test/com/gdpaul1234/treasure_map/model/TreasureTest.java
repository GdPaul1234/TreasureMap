package com.gdpaul1234.treasure_map.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreasureTest {

    @Test
    void whenRemainingTreasure_pickUpTreasure_ShouldDecreaseNbOfTreasure() {
        var t = new Treasure(1, 1, 2);

        assertTrue(t.pickUpTreasure());
        assertEquals(1, t.getNbTreasure());
    }

    @Test
    void whenNoTreasure_pickupTreasure_ShouldFailed() {
        var t = new Treasure(1, 1, 0);

        assertFalse(t.pickUpTreasure());
        assertEquals(0, t.getNbTreasure());
    }
}