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

    float stateTime = 0;


    /**
     * Student`s speed
     */
    public static float SPEED = 4f;
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
     */
    public Student(Vector2 position) {
        this.position = position;
        this.bounds.height = SIZE;
        this.bounds.width = SIZE;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setState(State newState) {
        this.state = newState;
    }

    public void update(float delta) {
        stateTime += delta;
        position.add(velocity.cpy().scl(delta));
    }

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setFacingLeft(boolean facingLeft) {
        this.facingLeft = facingLeft;
    }

    public float getStateTime() {
        return stateTime;
    }

    public State getState() {
        return state;
    }

    public boolean isFacingLeft() {
        return facingLeft;
    }
}
