package com.naukma.game.controller;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.naukma.game.entity.*;
import com.naukma.game.screens.GameScreen;

import java.util.HashMap;
import java.util.Map;

/**
 * Class WorldController
 */
public class WorldController {

    /**
     * LONG_JUMP_PRESS
     */
    private static final long LONG_JUMP_PRESS = 150L;
    /**
     * ACCELERATION
     */
    private static final float ACCELERATION = 200f;
    /**
     * GRAVITY
     */
    private static final float GRAVITY = -40f;
    /**
     * MAX_JUMP_SPEED
     */
    private static final float MAX_JUMP_SPEED = 10f;
    /**
     * DAMP
     */
    private static final float DAMP = 0.90f;
    /**
     * MAX_VEL
     */
    private static final float MAX_VEL = 2f;
    /**
     * keys
     */
    static Map<Keys, Boolean> keys = new HashMap<>();

    public static final float SOUND_VOLUME = 0.3f;

    Sound levelEnd = Gdx.audio.newSound(Gdx.files.internal("music/levelUp.mp3"));
    Sound coinPickup = Gdx.audio.newSound(Gdx.files.internal("music/coinPickup.wav"));

    static {
        keys.put(Keys.LEFT, false);
        keys.put(Keys.RIGHT, false);
        keys.put(Keys.JUMP, false);
        keys.put(Keys.FIRE, false);
    }

    /**
     * Student
     */
    private final Student student;
    /**
     * World
     */
    private final World world;
    /**
     * Pool
     */
    private final Pool<Rectangle> rectPool = new Pool<Rectangle>() {
        @Override
        protected Rectangle newObject() {
            return new Rectangle();
        }
    };
    /**
     * collectableBlock
     */
    private final Array<Block> collectableBlock = new Array<>();
    /**
     * collectableMark
     */
    private final Array<Mark> collectableMark = new Array<>();
    /**
     * collectableBonus
     */
    private final Array<Bonus> collectableBonus = new Array<>();
    /**
     * jumpPressedTime
     */
    private long jumpPressedTime;
    /**
     * jumpingPressed
     */
    private boolean jumpingPressed;
    /**
     * grounded
     */
    private boolean grounded = false;

    /**
     * World
     *
     * @param world world
     */
    public WorldController(World world) {
        this.world = world;
        this.student = world.getStudent();
    }

    /**
     * leftPressed
     */
    public void leftPressed() {
        keys.get(keys.put(Keys.LEFT, true));
    }

    /**
     * rightPressed
     */
    public void rightPressed() {
        keys.get(keys.put(Keys.RIGHT, true));
    }

    /**
     * jumpPressed
     */
    public void jumpPressed() {
        keys.get(keys.put(Keys.JUMP, true));
    }

    /**
     * firePressed
     */
    public void firePressed() {
        keys.get(keys.put(Keys.FIRE, true));
    }

    /**
     * leftReleased
     */
    public void leftReleased() {
        keys.get(keys.put(Keys.LEFT, false));
    }

    /**
     * rightReleased
     */
    public void rightReleased() {
        keys.get(keys.put(Keys.RIGHT, false));
    }

    /**
     * jumpReleased
     */
    public void jumpReleased() {
        jumpingPressed = false;
        keys.get(keys.put(Keys.JUMP, false));
    }

    /**
     * fireReleased
     */
    public void fireReleased() {
        keys.get(keys.put(Keys.FIRE, false));
    }

    /**
     * Update
     *
     * @param delta time
     */
    public void update(float delta) {
        processInput();

        setAcceleration(delta);

        checkCollisionWithBlocks(delta);

        checkForOverVelocity();

        student.update(delta);
    }

    private void setAcceleration(float delta) {
        if (grounded && student.getState().equals(Student.State.JUMPING)) {
            student.setState(Student.State.IDLE);
        }
        student.getAcceleration().y = GRAVITY;
        student.getAcceleration().scl(delta);
        student.getVelocity().add(student.getAcceleration().x, student.getAcceleration().y);
    }

    private void checkForOverVelocity() {
        student.getVelocity().x *= DAMP;

        if (student.getVelocity().x > MAX_VEL) {
            student.getVelocity().x = MAX_VEL;
        }

        if (student.getVelocity().x < -MAX_VEL) {
            student.getVelocity().x = -MAX_VEL;
        }
    }

    /**
     * checkCollisionWithBlocks
     *
     * @param delta time
     */
    private void checkCollisionWithBlocks(float delta) {
        student.getVelocity().scl(delta);
        Rectangle studentRect = rectPool.obtain();
        studentRect.set(student.getBounds().x, student.getBounds().y, student.getBounds().width, student.getBounds().height);

        float startX, endX;
        float startY = student.getBounds().y;
        float endY = (student.getBounds().y + student.getBounds().height);
        if (student.getVelocity().x < 0) {
            startX = endX = (int) Math.floor(student.getBounds().x + student.getVelocity().x);
        } else {
            startX = endX = (int) Math.floor(student.getBounds().x + student.getBounds().width + student.getVelocity().x);
        }


        populateCollectableBlocks(startX, startY, endX, endY);
        studentRect.x += student.getVelocity().x;
        world.getCollisionRects().clear();

        for (Block block : collectableBlock) {
            if (block == null) continue;
            if (studentRect.overlaps(block.getBounds())) {
                student.getVelocity().x = 0;
                world.getCollisionRects().add(block.getBounds());
                break;
            }
        }

        for (Mark mark : collectableMark) {
            if (mark == null) continue;
            if (studentRect.overlaps(mark.getBounds())) {
                switch (GameScreen.levelNumber) {
                    case 1:
                        GameScreen.firstPoints += 40;
                        break;
                    case 2:
                        GameScreen.secondPoints += 40;
                        break;
                    case 3:
                        GameScreen.thirdPoints += 40;
                        break;
                    case 4:
                        GameScreen.fourthPoints += 40;
                        break;
                    case 5:
                        GameScreen.fifthPoints += 40;
                        break;
                }
                levelEnd.play(SOUND_VOLUME);
                GameScreen.levelNumber++;
                GameScreen.isNextLevel = true;
                break;
            }
        }

        for (Bonus bonus : collectableBonus) {
            if (bonus == null) continue;
            if (studentRect.overlaps(bonus.getBounds())) {
                if (!world.getBonusIgnore().contains(bonus, false)) {
                    world.getBonusIgnore().add(bonus);
                    switch (GameScreen.levelNumber) {
                        case 1:
                            GameScreen.firstPoints += 6;
                            break;
                        case 2:
                            GameScreen.secondPoints += 6;
                            break;
                        case 3:
                            GameScreen.thirdPoints += 6;
                            break;
                        case 4:
                            GameScreen.fourthPoints += 6;
                            break;
                        case 5:
                            GameScreen.fifthPoints += 6;
                            break;
                    }
                    coinPickup.play(SOUND_VOLUME);
                }
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

        populateCollectableBlocks(startX, startY, endX, endY);

        studentRect.y += student.getVelocity().y;

        for (Block block : collectableBlock) {
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

        for (Mark mark : collectableMark) {
            if (mark == null) continue;
            if (studentRect.overlaps(mark.getBounds())) {
                // TODO
                break;
            }
        }

        for (Bonus bonus : collectableBonus) {
            if (bonus == null) continue;
            if (studentRect.overlaps(bonus.getBounds())) {
                // TODO
                break;
            }
        }


        studentRect.y = student.getPosition().y;

        student.getPosition().add(student.getVelocity());
        student.getBounds().x = student.getPosition().x;
        student.getBounds().y = student.getPosition().y;

        student.getVelocity().scl(1 / delta);

    }

    /**
     * populateCollectableBlocks
     *
     * @param startX startX
     * @param startY startY
     * @param endX   endX
     * @param endY   endY
     */
    private void populateCollectableBlocks(float startX, float startY, float endX, float endY) {
        collectableBlock.clear();
        collectableMark.clear();
        collectableBonus.clear();
        for (float x = startX; x <= endX; x++) {
            for (float y = startY; y <= endY; y++) {
                if (x >= 0 && x < world.getLevel().getWidth() && y >= 0 && y < world.getLevel().getHeight()) {
                    collectableBlock.add(world.getLevel().getBlock((int)x, (int)y));
                    collectableMark.add(world.getLevel().getMark((int)x, (int)y));
                    collectableBonus.add(world.getLevel().getBonus((int)x, (int)y));
                }
            }
        }
    }

    /**
     * processInput
     */
    private void processInput() {
        if (keys.get(Keys.JUMP)) {
            if (!student.getState().equals(Student.State.JUMPING)) {
                jumpingPressed = true;
                jumpPressedTime = System.currentTimeMillis();
                student.setState(Student.State.JUMPING);
                student.getVelocity().y = MAX_JUMP_SPEED;
                grounded = false;
            } else {
                if (jumpingPressed && ((System.currentTimeMillis() - jumpPressedTime) >= LONG_JUMP_PRESS)) {
                    jumpingPressed = false;
                } else {
                    if (jumpingPressed) {
                        student.getVelocity().y = MAX_JUMP_SPEED;
                    }
                }
            }
        } else if (keys.get(Keys.LEFT)) {
            student.setFacingLeft(true);
            if (!student.getState().equals(Student.State.JUMPING)) {
                student.setState(Student.State.WALKING);
            }
            student.getAcceleration().x = -ACCELERATION;
        } else if (keys.get(Keys.RIGHT)) {
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
    }

    /**
     * keys
     */
    public enum Keys {
        LEFT,
        RIGHT,
        JUMP,
        FIRE
    }

}
