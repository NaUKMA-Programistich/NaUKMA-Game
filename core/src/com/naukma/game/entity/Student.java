package com.naukma.game.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * class Student
 */
public class Student {

    /**
     * SIZE
     */
    public static final float SIZE = 0.75f;
    /**
     * position
     */
    Vector2 position;
    /**
     * acceleration
     */
    Vector2 acceleration = new Vector2();
    /**
     * velocity
     */
    Vector2 velocity = new Vector2();
    /**
     * bounds
     */
    Rectangle bounds = new Rectangle();
    /**
     * state
     */
    State state = State.IDLE;
    /**
     * facingLeft
     */
    boolean facingLeft = true;
    /**
     * stateTime
     */
    float stateTime = 0;

    /**
     * Constructor student
     *
     * @param position Vector
     */
    public Student(Vector2 position) {
        this.position = position;
        this.bounds.x = position.x;
        this.bounds.y = position.y;
        this.bounds.height = SIZE;
        this.bounds.width = SIZE;
    }

    /**
     * getPosition
     *
     * @return position
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * setPosition
     *
     * @param position Vector2
     */
    public void setPosition(Vector2 position) {
        this.position = position;
    }

    /**
     * update
     *
     * @param delta time
     */
    public void update(float delta) {
        stateTime += delta;
    }

    /**
     * getAcceleration
     *
     * @return Vector2
     */
    public Vector2 getAcceleration() {
        return acceleration;
    }

    /**
     * getVelocity
     *
     * @return velocity
     */
    public Vector2 getVelocity() {
        return velocity;
    }

    /**
     * getStateTime
     *
     * @return stateTime
     */
    public float getStateTime() {
        return stateTime;
    }

    /**
     * getState
     *
     * @return state
     */
    public State getState() {
        return state;
    }

    /**
     * setState
     *
     * @param newState state
     */
    public void setState(State newState) {
        this.state = newState;
    }

    /**
     * isFacingLeft
     *
     * @return facingLeft
     */
    public boolean isFacingLeft() {
        return facingLeft;
    }

    /**
     * setFacingLeft
     *
     * @param facingLeft facingLeft
     */
    public void setFacingLeft(boolean facingLeft) {
        this.facingLeft = facingLeft;
    }

    /**
     * getBounds
     *
     * @return Rectangle
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * enum with states student
     */
    public enum State {
        IDLE,
        WALKING,
        JUMPING,
    }

}
