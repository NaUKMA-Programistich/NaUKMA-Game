package com.naukma.game.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * class Block
 */
public class Block {
    /**
     * Size block
     */
    public static float SIZE = 1f;
    /**
     * Block`s position
     */
    Vector2 position = new Vector2();
    /**
     * Block`s bounds
     */
    Rectangle bounds = new Rectangle();
    /**
     * Block`s constructor
     * @param position Vector
     */
    public Block(Vector2 position) {
        this.position = position;
        this.bounds.width = SIZE;
        this.bounds.height = SIZE;
    }

    public Rectangle getBounds() { return bounds; }
    public void setBounds(Rectangle bounds) { this.bounds = bounds; }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
