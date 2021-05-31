package com.naukma.game.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * class Block
 */
public class Block {

    public static float SIZE = 1f;
    Vector2 position = new Vector2();
    Rectangle bounds = new Rectangle();

    public Block(Vector2 position) {
        this.position = position;
        this.bounds.setX(position.x);
        this.bounds.setY(position.y);
        this.bounds.width = SIZE;
        this.bounds.height = SIZE;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Vector2 getPosition() {
        return position;
    }
}


