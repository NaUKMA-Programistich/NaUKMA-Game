package com.naukma.game.entity;

import com.badlogic.gdx.math.Vector2;

/**
 * Class level
 */
public class Level {

    /**
     * width
     */
    private int width;
    /**
     * height
     */
    private int height;
    /**
     * blocks
     */
    private Block[][] blocks;
    /**
     * marks
     */
    private Mark[][] marks;
    /**
     * bonuses
     */
    private Bonus[][] bonuses;
    /**
     * spanPosition
     */
    private Vector2 spanPosition;

    /**
     * getWidth
     *
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * setWidth
     *
     * @param width width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * getHeight
     *
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * setHeight
     *
     * @param height height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * getBlocks
     *
     * @return Block
     */
    public Block[][] getBlocks() {
        return blocks;
    }

    /**
     * setBlocks
     *
     * @param blocks Block
     */
    public void setBlocks(Block[][] blocks) {
        this.blocks = blocks;
    }

    /**
     * getMarks
     *
     * @return Mark
     */
    public Mark[][] getMarks() {
        return marks;
    }

    /**
     * setMarks
     *
     * @param marks Mark
     */
    public void setMarks(Mark[][] marks) {
        this.marks = marks;
    }

    /**
     * getBonuses
     *
     * @return bonuses
     */
    public Bonus[][] getBonuses() {
        return bonuses;
    }

    /**
     * setBonuses
     *
     * @param bonuses Bonus
     */
    public void setBonuses(Bonus[][] bonuses) {
        this.bonuses = bonuses;
    }

    /**
     * getBlock
     *
     * @param x x
     * @param y y
     * @return Block
     */
    public Block getBlock(int x, int y) {
        return blocks[x][y];
    }

    /**
     * getMark
     *
     * @param x x
     * @param y y
     * @return Mark
     */
    public Mark getMark(int x, int y) {
        return marks[x][y];
    }

    /**
     * getBonus
     *
     * @param x x
     * @param y y
     * @return Bonus
     */
    public Bonus getBonus(int x, int y) {
        return bonuses[x][y];
    }

    /**
     * getSpanPosition
     *
     * @return Vector2
     */
    public Vector2 getSpanPosition() {
        return spanPosition;
    }

    /**
     * setSpanPosition
     *
     * @param spanPosition Vector2
     */
    public void setSpanPosition(Vector2 spanPosition) {
        this.spanPosition = spanPosition;
    }

}