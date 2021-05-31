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


    public static final float SIZE = 0.5f;

    Vector2 position = new Vector2();
    Vector2 acceleration = new Vector2();
    Vector2 velocity = new Vector2();
    Rectangle bounds = new Rectangle();
    State state = State.IDLE;
    boolean facingLeft = true;
    float stateTime = 0;
    boolean longJump = false;

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

    public Vector2 getPosition() {
        return position;
    }

    public void setState(State newState) {
        this.state = newState;
    }

    public void update(float delta) {
        stateTime += delta;
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

    public void setPosition(Vector2 position) {
        this.position = position;
        this.bounds.setX(position.x);
        this.bounds.setY(position.y);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public boolean isLongJump() {
        return longJump;
    }


    public void setLongJump(boolean longJump) {
        this.longJump = longJump;
    }

}
