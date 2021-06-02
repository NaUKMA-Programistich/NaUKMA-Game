package com.naukma.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;
import com.naukma.game.entity.Block;
import com.naukma.game.entity.Bonus;
import com.naukma.game.entity.Level;
import com.naukma.game.entity.Mark;

/**
 * Class LevelLoader
 */
public class LevelLoader {

    /**
     * LEVEL_PREFIX
     */
    private static final String LEVEL_PREFIX = "levels/level-";

    /**
     * black
     */
    private static final int BLOCK = 0x000000;
    /**
     * white
     */
    private static final int EMPTY = 0xffffff;
    /**
     * blue
     */
    private static final int START_POS = 0x2200ff;
    /**
     * yellow
     */
    private static final int MARK_FINAL = 0xffff00;
    /**
     * red
     */
    private static final int BONUS_POINT = 0xff0000;

    /**
     * loadLevel
     *
     * @param number numberLevel
     * @return Level
     */
    public static Level loadLevel(int number) {
        Level level = new Level();
        Pixmap pixmap = new Pixmap(Gdx.files.internal(LEVEL_PREFIX + number + ".png"));
        level.setWidth(pixmap.getWidth());
        level.setHeight(pixmap.getHeight());

        Block[][] blocks = new Block[level.getWidth()][level.getHeight()];
        Mark[][] marks = new Mark[level.getWidth()][level.getHeight()];
        Bonus[][] bonuses = new Bonus[level.getWidth()][level.getHeight()];
        for (int col = 0; col < level.getWidth(); col++) {
            for (int row = 0; row < level.getHeight(); row++) {
                blocks[col][row] = null;
                marks[col][row] = null;
                bonuses[col][row] = null;
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
                } else if (pixel == MARK_FINAL) {
                    marks[col][iRow] = new Mark(new Vector2(col, iRow));
                } else if (pixel == BONUS_POINT) {
                    bonuses[col][iRow] = new Bonus(new Vector2(col, iRow));
                }
            }
        }
        level.setBlocks(blocks);
        level.setMarks(marks);
        level.setBonuses(bonuses);
        return level;
    }

}
