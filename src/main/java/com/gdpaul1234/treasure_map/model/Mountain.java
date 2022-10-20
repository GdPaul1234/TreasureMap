package com.gdpaul1234.treasure_map.model;

public class Mountain extends Field {
    public Mountain(int x, int y) {
        super(x, y);
    }

    @Override
    public String toString() {
        return String.format("M - %d - %d", this.x, this.y);
    }
}
