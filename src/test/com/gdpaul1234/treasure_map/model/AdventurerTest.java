package com.gdpaul1234.treasure_map.model;

import com.gdpaul1234.treasure_map.enums.Orientation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class AdventurerTest {
    private Map map;

    @BeforeEach
    void init() {
        map = new Map(3, 4);
        map.addField(new Mountain(1, 1));
        map.addField(new Mountain(2, 2));
        map.addField(new Treasure(0, 3, 2));
        map.addField(new Treasure(1, 3, 1));
    }

    @Nested
    class describeAdvance {
        @Test
        void whenNoObstacle_move_ShouldAdvance() {
            var adventurer = new Adventurer(1, 1, map, "Indiana", "S", "A");
            map.addField(adventurer);

            adventurer.move();

            assertEquals(1, adventurer.getX());
            assertEquals(2, adventurer.getY());
        }

        @Test
        void whenMountain_move_ShouldBeIgnored() {
            var adventurer = new Adventurer(1, 2, map, "Indiana", "E", "A");
            map.addField(adventurer);

            adventurer.move();

            assertEquals(1, adventurer.getX());
            assertEquals(2, adventurer.getY());
        }

        @Test
        void whenTreasure_move_ShouldMoveAndPickUpTreasure() {
            var adventurer = new Adventurer(0, 2, map, "Indiana", "S", "A");
            var treasure = (Treasure) map.getFieldAt(0, 3);
            map.addField(adventurer);

            adventurer.move();

            assertEquals(0, adventurer.getX());
            assertEquals(3, adventurer.getY());
            assertEquals(1, treasure.getNbTreasure());
        }
    }

    @Nested
    class describeTurnLeftOrRight {
        @Test
        void whenTurnLeft_move_ShouldChangeOrientationOfAdventurer() {
            var adventurer = new Adventurer(1, 1, map, "Indiana", "N", "G");
            map.addField(adventurer);

            adventurer.move();

            assertEquals(Orientation.O, adventurer.getOrientation());
        }

        @Test
        void whenTurnRight_move_ShouldChangeOrientationOfAdventurer() {
            var adventurer = new Adventurer(1, 1, map, "Indiana", "N", "D");
            map.addField(adventurer);

            adventurer.move();

            assertEquals(Orientation.E, adventurer.getOrientation());
        }
    }

    @Nested
    class describeSimulateAdventurerMovements {
        @Test
        void whenMultipleMoves_move_ShouldChangePositionAnCollectedTreasuresOfAdventurer() {
            var moves = "AADADAGGA";
            var adventurer = new Adventurer(1, 1, map, "Lara", "S", moves);

            IntStream.range(0, moves.length()).forEach(i -> adventurer.move());

            assertEquals(0, adventurer.getX());
            assertEquals(3, adventurer.getY());
            assertEquals(Orientation.S, adventurer.getOrientation());
            assertEquals(3, adventurer.getCollectedTreasure());
        }
    }
}