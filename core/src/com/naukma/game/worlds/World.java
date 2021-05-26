package com.naukma.game.worlds;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.naukma.game.entity.Block;
import com.naukma.game.entity.Student;

/**
 * Class word
 */
public class World {

    /**
     * Array with all block
     */
    Array<Block> blocks = new Array<>();
    /**
     * Our player
     */
    Student student;

    public Array<Block> getBlocks() {
        return blocks;
    }

    public Student getStudent() {
        return student;
    }

    public World() {
        createDemoWorld();
    }

    public void createDemoWorld() {
        student = new Student(new Vector2(7, 2), 4f);

        for (int i = 0; i < 10; i++) {
            blocks.add(new Block(new Vector2(i, 0)));
            blocks.add(new Block(new Vector2(i, 7)));
            if (i > 2)
                blocks.add(new Block(new Vector2(i, 1)));
        }
        blocks.add(new Block(new Vector2(9, 2)));
        blocks.add(new Block(new Vector2(9, 3)));
        blocks.add(new Block(new Vector2(9, 4)));
        blocks.add(new Block(new Vector2(9, 5)));
        blocks.add(new Block(new Vector2(6, 3)));
        blocks.add(new Block(new Vector2(6, 4)));
        blocks.add(new Block(new Vector2(6, 5)));
    }

}
