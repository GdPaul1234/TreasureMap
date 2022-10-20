package com.gdpaul1234.treasure_map.util;

import com.gdpaul1234.treasure_map.enums.Movement;
import com.gdpaul1234.treasure_map.enums.Orientation;
import com.gdpaul1234.treasure_map.model.Adventurer;
import com.gdpaul1234.treasure_map.model.Map;
import com.gdpaul1234.treasure_map.model.Mountain;
import com.gdpaul1234.treasure_map.model.Treasure;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MoveHelperTest {
    @Test
    void givenOrientation_getDx_ShouldReturnNextMove() {
        assertEquals(-1, MoveHelper.getDx(Orientation.O));
        assertEquals(1, MoveHelper.getDx(Orientation.E));
        assertEquals(0, MoveHelper.getDx(Orientation.N));
        assertEquals(0, MoveHelper.getDx(Orientation.S));
    }

    @Test
    void givenOrientation_getDy_ShouldReturnNextMove() {
        assertEquals(-1, MoveHelper.getDy(Orientation.N));
        assertEquals(1, MoveHelper.getDy(Orientation.S));
        assertEquals(0, MoveHelper.getDy(Orientation.O));
        assertEquals(0, MoveHelper.getDy(Orientation.E));
    }

    @Test
    void whenOutofBound_canMove_ShouldReturnFalse() {
        var map = new Map(2, 2);

        assertFalse(MoveHelper.canMove(map, 2, 1));
        assertFalse(MoveHelper.canMove(map, 0, 2));
        assertFalse(MoveHelper.canMove(map, -1, 0));
        assertFalse(MoveHelper.canMove(map, 0, -1));
    }

    @Test
    void whenMountain_canMove_ShouldReturnFalse() {
        var map = new Map(2, 2);
        map.addField(new Mountain(1, 0));

        assertFalse(MoveHelper.canMove(map, 1, 0));
    }

    @Test
    void whenAdventurer_canMove_ShoudReturnFalse() {
        var map = new Map(2, 2);
        map.addField(new Adventurer(1, 0, map,
                "Paul", "N", "AADADAGGA"));

        assertFalse(MoveHelper.canMove(map, 1, 0));
    }

    @Test
    void whenTreasure_canMove_ShouldReturnTrue() {
        var map = new Map(2, 2);
        map.addField(new Treasure(1, 0, 5));

        assertTrue(MoveHelper.canMove(map, 1, 0));
    }

    @Test
    void whenPlain_canMove_ShouldReturnTrue() {
        var map = new Map(2, 2);

        assertTrue(MoveHelper.canMove(map, 0, 0));
    }

    @Test
    void whenTurnLeft_getNextOrientation() {
        Orientation[] inputs = {Orientation.N, Orientation.E, Orientation.S, Orientation.O};
        Orientation[] expecteds = {Orientation.O, Orientation.N, Orientation.E, Orientation.S};

        for (int i = 0; i < inputs.length; i++) {
            assertEquals(expecteds[i], MoveHelper.getNextOrientation(inputs[i], Movement.G));
        }
    }

    @Test
    void whenTurnRight_getNextOrientation() {
        Orientation[] inputs = {Orientation.N, Orientation.E, Orientation.S, Orientation.O};
        Orientation[] expecteds = {Orientation.E, Orientation.S, Orientation.O, Orientation.N};

        for (int i = 0; i < inputs.length; i++) {
            assertEquals(expecteds[i], MoveHelper.getNextOrientation(inputs[i], Movement.D));
        }
    }

    @Test
    void whenAdvance_getNextOrientation_ShouldNotChangeOrientation() {
        Orientation[] inputs = {Orientation.N, Orientation.E, Orientation.S, Orientation.O};

        for (Orientation input : inputs) {
            assertEquals(input, MoveHelper.getNextOrientation(input, Movement.A));
        }
    }
}