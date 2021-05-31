package com.naukma.game.controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.naukma.game.entity.Block;
import com.naukma.game.entity.Student;
import com.naukma.game.entity.World;

import java.util.HashMap;
import java.util.Map;


public class WorldController {

    public enum Keys {
        LEFT,
        RIGHT,
        JUMP,
        FIRE
    }

    private static final long LONG_JUMP_PRESS = 150L;
    private static final float ACCELERATION = 20f;
    private static final float GRAVITY = -20f;
    private static final float MAX_JUMP_SPEED = 7f;
    private static final float DAMP = 0.90f;
    private static final float MAX_VEL = 2f;

    private final World world;
    public final Student student;
    private long jumpPressedTime;
    private boolean jumpingPressed;
    private boolean grounded = false;
    private final Pool<Rectangle> rectPool = new Pool<Rectangle>() {
        @Override
        protected Rectangle newObject() {
            return new Rectangle();
        }
    };

    static Map<Keys, Boolean> keys = new HashMap<>();

    static {
        keys.put(Keys.LEFT, false);
        keys.put(Keys.RIGHT, false);
        keys.put(Keys.JUMP, false);
        keys.put(Keys.FIRE, false);
    }

    private final Array<Block> collidable = new Array<Block>();

    public WorldController(World world) {
        this.world = world;
        this.student = world.getStudent();
    }

    public void leftPressed() {
        keys.get(keys.put(Keys.LEFT, true));
    }

    public void rightPressed() {
        keys.get(keys.put(Keys.RIGHT, true));
    }

    public void jumpPressed() {
        keys.get(keys.put(Keys.JUMP, true));
    }

    public void firePressed() {
        keys.get(keys.put(Keys.FIRE, true));
    }


    public void leftReleased() {
        keys.get(keys.put(Keys.LEFT, false));
    }

    public void rightReleased() {
        keys.get(keys.put(Keys.RIGHT, false));
    }

    public void jumpReleased() {
        jumpingPressed = false;
        keys.get(keys.put(Keys.JUMP, false));
    }

    public void fireReleased() {
        keys.get(keys.put(Keys.FIRE, false));
    }

    public void update(float delta) {
        processInput();
        if (grounded && student.getState().equals(Student.State.JUMPING)) {
            student.setState(Student.State.IDLE);

        }
        student.getAcceleration().y = GRAVITY;
        student.getAcceleration().scl(delta);
        student.getVelocity().add(student.getAcceleration().x, student.getAcceleration().y);

        checkCollisionWithBlocks(delta);

        student.getVelocity().x *= DAMP;


        if (student.getVelocity().x > MAX_VEL) {
            student.getVelocity().x = MAX_VEL;
        }

        if (student.getVelocity().x < -MAX_VEL) {
            student.getVelocity().x = -MAX_VEL;
        }

        student.update(delta);
    }

    private void checkCollisionWithBlocks(float delta) {
        student.getVelocity().scl(delta);
        Rectangle studentRect = rectPool.obtain();
        studentRect.set(student.getBounds().x, student.getBounds().y, student.getBounds().width, student.getBounds().height);

        int startX, endX;
        int startY = (int) student.getBounds().y;
        int endY = (int) (student.getBounds().y + student.getBounds().height);
        if (student.getVelocity().x < 0) {
            startX = endX = (int) Math.floor(student.getBounds().x + student.getVelocity().x);
        } else {
            startX = endX = (int) Math.floor(student.getBounds().x + student.getBounds().width + student.getVelocity().x);
        }


        populateCollidableBlocks(startX, startY, endX, endY);
        studentRect.x += student.getVelocity().x;
        world.getCollisionRects().clear();

        for (Block block : collidable) {
            if (block == null) continue;
            if (studentRect.overlaps(block.getBounds())) {
                student.getVelocity().x = 0;
                world.getCollisionRects().add(block.getBounds());
                break;
            }
        }

        studentRect.x = student.getPosition().x;

        startX = (int) student.getBounds().x;
        endX = (int) (student.getBounds().x + student.getBounds().width);
        if (student.getVelocity().y < 0) {
            startY = endY = (int) Math.floor(student.getBounds().y + student.getVelocity().y);
        } else {
            startY = endY = (int) Math.floor(student.getBounds().y + student.getBounds().height + student.getVelocity().y);
        }

        populateCollidableBlocks(startX, startY, endX, endY);

        studentRect.y += student.getVelocity().y;

        for (Block block : collidable) {
            if (block == null) continue;
            if (studentRect.overlaps(block.getBounds())) {
                if (student.getVelocity().y < 0) {
                    grounded = true;
                }
                student.getVelocity().y = 0;
                world.getCollisionRects().add(block.getBounds());
                break;
            }
        }
        studentRect.y = student.getPosition().y;

        student.getPosition().add(student.getVelocity());
        student.getBounds().x = student.getPosition().x;
        student.getBounds().y = student.getPosition().y;

        student.getVelocity().scl(1 / delta);

    }

    private void populateCollidableBlocks(int startX, int startY, int endX, int endY) {
        collidable.clear();
        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                if (x >= 0 && x < world.getLevel().getWidth() && y >= 0 && y < world.getLevel().getHeight()) {
                    collidable.add(world.getLevel().get(x, y));
                }
            }
        }
    }


    private void processInput() {
        if (keys.get(Keys.JUMP)) {
            if (!student.getState().equals(Student.State.JUMPING)) {
                jumpingPressed = true;
                jumpPressedTime = System.currentTimeMillis();
                student.setState(Student.State.JUMPING);
                student.getVelocity().y = MAX_JUMP_SPEED;
                grounded = false;
            }
            else {
                if (jumpingPressed && ((System.currentTimeMillis() - jumpPressedTime) >= LONG_JUMP_PRESS)) {
                    jumpingPressed = false;
                } else {
                    if (jumpingPressed) {
                        student.getVelocity().y = MAX_JUMP_SPEED;
                    }
                }
            }
        }
        else if (keys.get(Keys.LEFT)) {
            student.setFacingLeft(true);
            if (!student.getState().equals(Student.State.JUMPING)) {
                student.setState(Student.State.WALKING);
            }
            student.getAcceleration().x = -ACCELERATION;
        }
        else if (keys.get(Keys.RIGHT)) {
            student.setFacingLeft(false);
            if (!student.getState().equals(Student.State.JUMPING)) {
                student.setState(Student.State.WALKING);
            }
            student.getAcceleration().x = ACCELERATION;
        } else {
            if (!student.getState().equals(Student.State.JUMPING)) {
                student.setState(Student.State.IDLE);
            }
            student.getAcceleration().x = 0;
        }

//        if ((keys.get(Keys.LEFT) && keys.get(Keys.RIGHT)) || (!keys.get(Keys.LEFT) && !(keys.get(Keys.RIGHT)))) {
//            student.setState(Student.State.IDLE);
//            student.getAcceleration().x = 0;
//            student.getVelocity().x = 0;
//        }

    }


}
