package com.naukma.game.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.naukma.game.controller.LevelLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Class word
 */
public class World {

    Array<Block> blocks = new Array<>();
    Student student;
    Level level;

    public Array<Block> getBlocks() {
        return blocks;
    }

    Array<Rectangle> collisionRects = new Array<Rectangle>();

    public List<Block> getDrawableBlocks(int width, int height) {
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
        List<Block> blocks = new ArrayList<>();
        Block block;
        for (int col = x; col <= x2; col++) {
            for (int row = y; row <= y2; row++) {
                block = level.getBlocks()[col][row];
                if (block != null) {
                    blocks.add(block);
                }
            }
        }
        return blocks;
    }

    public void createDemoWorld() {
//        level = LevelLoader.loadLevel(1);
//        student = new Student(level.getSpanPosition());
//        level = new Level();
//        student = new Student(new Vector2(7, 2));
    }

    public Array<Rectangle> getCollisionRects() {
        return collisionRects;
    }

    public Student getStudent() {
        return student;
    }

    public Level getLevel() {
        return level;
    }

    public World() {
        createDemoWorld();
    }


}
