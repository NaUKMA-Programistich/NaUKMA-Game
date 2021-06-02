package com.naukma.game.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * class Block
 */
public class Block {

    /**
     * SIZE
     */
    public static float SIZE = 1f;
    /**
     * position
     */
    Vector2 position;
    /**
     * bounds
     */
    Rectangle bounds = new Rectangle();

    /**
     * Constructor Block
     *
     * @param position Vector2
     */
    public Block(Vector2 position) {
        this.position = position;
        this.bounds.setX(position.x);
        this.bounds.setY(position.y);
        this.bounds.width = SIZE;
        this.bounds.height = SIZE;
    }

    /**
     * Getter
     *
     * @return getBounds
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Getter
     *
     * @return getPosition
     */
    public Vector2 getPosition() {
        return position;
    }
}


