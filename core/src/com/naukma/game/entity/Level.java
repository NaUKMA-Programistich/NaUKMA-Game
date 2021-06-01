package com.naukma.game.entity;

import com.badlogic.gdx.math.Vector2;

import java.util.Arrays;

public class Level {

    private int width;
    private int height;
    private Block[][] blocks;
    private Mark[][] marks;
    private Bonus[][] bonuses;
    private Vector2 spanPosition;


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Block[][] getBlocks() {
        return blocks;
    }

    public Mark[][] getMarks() {
        return marks;
    }

    public Bonus[][] getBonuses() {
        return bonuses;
    }

    public void setMarks(Mark[][] marks) {
        this.marks = marks;
    }

    public void setBonuses(Bonus[][] bonuses) {
        this.bonuses = bonuses;
    }

    public void setBlocks(Block[][] blocks) {
        this.blocks = blocks;
    }

    public Level() {
        loadDemoLevel();
    }

    public Block getBlock(int x, int y) {
        return blocks[x][y];
    }
    public Mark getMark(int x, int y) {
        return marks[x][y];
    }
    public Bonus getBonus(int x, int y) {
        return bonuses[x][y];
    }

    public Vector2 getSpanPosition() {
        return spanPosition;
    }

    public void setSpanPosition(Vector2 spanPosition) {
        this.spanPosition = spanPosition;
    }

    private void loadDemoLevel() {
        width = 20;
        height = 20;
        blocks = new Block[width][height];
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                blocks[col][row] = null;
            }
        }

        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                blocks[col][row] = new Block(new Vector2(col, row));
            }
//            blocks[col][0] = new Block(new Vector2(col, 0));
//            blocks[col][6] = new Block(new Vector2(col, 6));
        }
    }

    @Override
    public String toString() {
        return "Level{" +
                "width=" + width +
                ", height=" + height +
                ", blocks=" + Arrays.toString(blocks) +
                ", spanPosition=" + spanPosition +
                '}';
    }
}