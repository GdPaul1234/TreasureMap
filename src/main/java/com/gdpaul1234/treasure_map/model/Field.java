package com.gdpaul1234.treasure_map.model;

public abstract class Field {
    protected int x;
    protected int y;

    public Field(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
