package com.gdpaul1234.treasure_map.util;

import com.gdpaul1234.treasure_map.enums.Movement;
import com.gdpaul1234.treasure_map.enums.Orientation;
import com.gdpaul1234.treasure_map.model.*;

import java.util.List;

public class MoveHelper {
    public static int getDx(Orientation orientation) {
        return switch (orientation) {
            case O -> -1;
            case E -> 1;
            default -> 0;
        };
    }

    public static int getDy(Orientation orientation) {
        return switch (orientation) {
            case N -> -1;
            case S -> 1;
            default -> 0;
        };
    }

    public static boolean canMove(Map map, int newX, int newY) {
        if (newX < 0 || newX >= map.getWidth()) return false;
        if (newY < 0 || newY >= map.getHeight()) return false;

        Field nextField = map.getFieldAt(newX, newY);
        return switch (nextField) {
            case Mountain m -> false; // Mountains are insurmountable obstacles for adventurers
            case Adventurer a -> false; // There can only be one adventurer at a time on the same square
            case Treasure t, null -> true;
            default -> throw new IllegalStateException("Unexpected value: " + nextField);
        };
    }

    public static Orientation getNextOrientation(Orientation currentOrientation, Movement movement) {
        List<Orientation> orientations = List.of(Orientation.N, Orientation.E, Orientation.S, Orientation.O);

        int currentIdx = orientations.indexOf(currentOrientation);
        int dIdx = switch (movement) {
            case G -> -1;
            case D -> 1;
            default -> 0;
        };
        int nextIdx = (currentIdx + dIdx + orientations.size()) % orientations.size();
        return orientations.get(nextIdx);
    }
}
