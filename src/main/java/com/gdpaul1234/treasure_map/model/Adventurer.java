package com.gdpaul1234.treasure_map.model;

import com.gdpaul1234.treasure_map.enums.Movement;
import com.gdpaul1234.treasure_map.enums.Orientation;

import java.util.List;
import java.util.stream.Collectors;

import static com.gdpaul1234.treasure_map.util.MoveHelper.*;

public class Adventurer extends Field {
    private final String name;
    private final List<Movement> moves;
    private Orientation orientation;
    private int collectedTreasure;
    private final Map context;


    public Adventurer(int x, int y, Map context,
                      String name, String orientation,
                      String movements) {
        super(x, y);
        this.context = context;

        this.name = name;
        this.orientation = Orientation.valueOf(orientation);
        this.moves = movements.chars()
                .mapToObj(Character::toString)
                .map(Movement::valueOf)
                .collect(Collectors.toList());
        this.collectedTreasure = 0;
    }

    public String getName() {
        return name;
    }

    public void move() {
        Movement nextMove = this.moves.remove(0);

        switch (nextMove) {
            case A -> {
                int nextX = this.x + getDx(this.orientation);
                int nextY = this.y + getDy(this.orientation);

                if (canMove(this.context, nextX, nextY)) {
                    this.x = nextX;
                    this.y = nextY;

                    Field field = this.context.getFieldAt(this.x, this.y);
                    if (field instanceof Treasure && ((Treasure) field).pickUpTreasure()) {
                        this.collectedTreasure++;
                    }
                }
            }
            case G, D -> this.orientation = getNextOrientation(this.orientation, nextMove);
        }
    }

    @Override
    public String toString() {
        return String.format("A - %s - %d - %d - %s - %d",
                this.name, this.x, this.y, this.orientation, this.collectedTreasure);
    }
}
