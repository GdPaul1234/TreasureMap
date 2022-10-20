package com.gdpaul1234.treasure_map.model;

public class Treasure extends Field {
    private int nbTreasure;

    public Treasure(int x, int y, int nbTreasure) {
        super(x, y);
        this.nbTreasure = nbTreasure;
    }

    /**
     * Pick up one treasure
     *
     * @return true if successful, false otherwise
     */
    public boolean pickUpTreasure() {
        if (this.nbTreasure > 0) {
            this.nbTreasure--;
            return true;
        }
        return false;
    }

    public int getNbTreasure() {
        return nbTreasure;
    }

    @Override
    public String toString() {
        return String.format("T - %d - %d - %d",
                this.x, this.y, this.nbTreasure);
    }
}
