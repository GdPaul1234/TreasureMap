package com.gdpaul1234.treasure_map.model;

import java.util.*;

public class Map {
    private final int height;
    private final int width;
    private final List<Field> fields = new ArrayList<>();

    public Map(int width, int height) {
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public <T extends Field> List<Field> getFieldOf(Class<T> clazz) {
        return this.fields.stream()
                .filter(f -> f.getClass() == clazz)
                .toList();
    }

    public Field getFieldAt(int x, int y) {
        Field field = this.fields.stream()
                .filter(f -> f.x == x && f.y == y)
                .findFirst()
                .orElse(null);

        if (field == null) {
            // check bounds
            var isOutOfX = x < 0 || x >= this.getWidth();
            var isOutOfY = y < 0 || y >= this.getHeight();
            if (isOutOfX || isOutOfY) {
                throw new IndexOutOfBoundsException();
            }
        }
        return field;
    }

    public void addField(Field field) {
        this.fields.add(field);
    }
}
