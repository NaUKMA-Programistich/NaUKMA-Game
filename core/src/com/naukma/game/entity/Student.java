package com.naukma.game.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * class Student
 */
public class Student {

    /**
     * enum with states student
     */
    public enum State {
        IDLE, // не движется, не прыгает и жив
        WALKING, // движение влево или вправо
        JUMPING, // прижок
        DYING // дед инсайд
    }

    /**
     * Student`s speed
     */
    public float SPEED;
    /**
     * Student`s jump
     */
    public static final float JUMP_VELOCITY = 1f;
    /**
     * Student`s size
     */
    public static final float SIZE = 0.5f;
    /**
     * Student`s position
     */
    Vector2 position = new Vector2();
    /**
     * Student`s acceleration
     */
    Vector2 acceleration = new Vector2();
    /**
     * Student`s velocity
     */
    Vector2 velocity = new Vector2();
    /**
     * Student`s bounds
     */
    Rectangle bounds = new Rectangle();
    /**
     * Student`s state
     */
    State state = State.IDLE;
    /**
     * Student`s face
     */
    boolean facingLeft = true;

    /**
     * Constructor student
     *
     * @param position Vector
     * @param SPEED    speed
     */
    public Student(Vector2 position, float SPEED) {
        this.SPEED = SPEED;
        this.position = position;
        this.bounds.height = SIZE;
        this.bounds.width = SIZE;
    }


    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
