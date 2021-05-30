package com.naukma.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;
import com.naukma.game.entity.Block;
import com.naukma.game.entity.Level;

public class LevelLoader {

    private static final String LEVEL_PREFIX    = "levels/level-";

    private static final int    BLOCK           = 0x000000; // black
    private static final int    EMPTY           = 0xffffff; // white
    private static final int    START_POS       = 0x0000ff; // blue

    public static Level loadLevel(int number) {
        Level level = new Level();
        Pixmap pixmap = new Pixmap(Gdx.files.internal(LEVEL_PREFIX + number + ".png"));
        level.setWidth(pixmap.getWidth());
        level.setHeight(pixmap.getHeight());

        Block[][] blocks = new Block[level.getWidth()][level.getHeight()];
        for (int col = 0; col < level.getWidth(); col++) {
            for (int row = 0; row < level.getHeight(); row++) {
                blocks[col][row] = null;
            }
        }

        for (int row = 0; row < level.getHeight(); row++) {
            for (int col = 0; col < level.getWidth(); col++) {
                int pixel = (pixmap.getPixel(col, row) >>> 8) & 0xffffff;
                int iRow = level.getHeight() - 1 - row;
                if (pixel == BLOCK) {
                    blocks[col][iRow] = new Block(new Vector2(col, iRow));
                } else if (pixel == START_POS) {
                    level.setSpanPosition(new Vector2(col, iRow));
                }
            }
        }
        level.setBlocks(blocks);
        return level;
    }

}
