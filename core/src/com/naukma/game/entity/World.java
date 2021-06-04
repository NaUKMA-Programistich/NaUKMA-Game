package com.naukma.game.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.naukma.game.controller.LevelLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Class word
 */
public class World {

    /**
     * Array
     */
    private final Array<Rectangle> collisionRects = new Array<>();
    /**
     * Student
     */
    private Student student;
    /**
     * Level
     */
    private Level level;

    private final Array<Bonus> bonusIgnore = new Array<>();

    public Array<Bonus> getBonusIgnore() {
        return bonusIgnore;
    }

    /**
     * Constructor World
     */
    public World() {
        createWorld(1);
    }

    public World(int levelNum){
       createWorld(levelNum);
    }

    /**
     * getCollisionRects
     *
     * @return Array
     */
    public Array<Rectangle> getCollisionRects() {
        return collisionRects;
    }

    /**
     * getStudent
     *
     * @return Student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * getLevel
     *
     * @return level
     */
    public Level getLevel() {
        return level;
    }

    /**
     * getDrawableBlocks
     *
     * @param width  width
     * @param height height
     * @return List
     */
    public List<Block> getDrawableBlocks(int width, int height) {
        int[] needs = getXY(width, height);
        List<Block> blocks = new ArrayList<>();
        Block block;
        for (int col = needs[0]; col <= needs[2]; col++) {
            for (int row = needs[1]; row <= needs[3]; row++) {
                block = level.getBlocks()[col][row];
                if (block != null) {
                    blocks.add(block);
                }
            }
        }
        return blocks;
    }

    /**
     * getDrawableMark
     *
     * @param width  width
     * @param height height
     * @return List
     */
    public List<Mark> getDrawableMark(int width, int height) {
        int[] needs = getXY(width, height);
        List<Mark> marks = new ArrayList<>();
        Mark mark;
        for (int col = needs[0]; col <= needs[2]; col++) {
            for (int row = needs[1]; row <= needs[3]; row++) {
                mark = level.getMarks()[col][row];
                if (mark != null) {
                    marks.add(mark);
                }
            }
        }
        return marks;
    }

    /**
     * getDrawableBonus
     *
     * @param width  width
     * @param height height
     * @return List
     */
    public List<Bonus> getDrawableBonus(int width, int height) {
        int[] needs = getXY(width, height);
        List<Bonus> bonuses = new ArrayList<>();
        Bonus bonus;
        for (int col = needs[0]; col <= needs[2]; col++) {
            for (int row = needs[1]; row <= needs[3]; row++) {
                bonus = level.getBonuses()[col][row];
                if (bonus != null) {
                    bonuses.add(bonus);
                }
            }
        }
        return bonuses;
    }

    public int[] getXY(int width, int height){
        int x = (int) student.getPosition().x - width;
        int y = (int) student.getPosition().y - height;
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        int x2 = x + 2 * width;
        int y2 = y + 2 * height;
        if (x2 > level.getWidth()) {
            x2 = level.getWidth() - 1;
        }
        if (y2 > level.getHeight()) {
            y2 = level.getHeight() - 1;
        }
        return new int[]{x,y,x2,y2};
    }

    /**
     * Create World
     *
     * @param levelNum levelNum
     */
    private void createWorld(int levelNum){
        level = LevelLoader.loadLevel(levelNum);
        student = new Student(level.getSpanPosition());
    }

}
