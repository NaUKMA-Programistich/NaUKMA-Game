package com.naukma.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.naukma.game.entity.*;

/**
 * Class render world
 */
public class WorldRenderer {

    /**
     * CAMERA_WIDTH
     */
    private static final float CAMERA_WIDTH = 32f;
    /**
     * CAMERA_HEIGHT
     */
    private static final float CAMERA_HEIGHT = 18f;
    /**
     * RUNNING_FRAME_DURATION
     */
    private static final float RUNNING_FRAME_DURATION = 0.06f;

    /**
     * World
     */
    private final World world;
    /**
     * OrthographicCamera
     */
    private final OrthographicCamera cam;

    /**
     * studentIdleLeft
     */
    private TextureRegion studentIdleLeft;
    /**
     * studentIdleRight
     */
    private TextureRegion studentIdleRight;
    /**
     * studentJumpLeft
     */
    private TextureRegion studentJumpLeft;
    /**
     * studentFallLeft
     */
    private TextureRegion studentFallLeft;
    /**
     * studentJumpRight
     */
    private TextureRegion studentJumpRight;
    /**
     * studentFallRight
     */
    private TextureRegion studentFallRight;
    /**
     * blockTexture
     */
    private TextureRegion blockTexture;
    /**
     * markTexture
     */
    private TextureRegion markTexture;
    /**
     * bonusTexture
     */
    private TextureRegion bonusTexture;

    /**
     * walkLeftAnimation
     */
    private Animation<? extends TextureRegion> walkLeftAnimation;
    /**
     * walkRightAnimation
     */
    private Animation<? extends TextureRegion> walkRightAnimation;
    /**
     * ShapeRenderer
     */
    private final ShapeRenderer debugRenderer = new ShapeRenderer();

    /**
     * Assets
     */
    private final SpriteBatch spriteBatch;
    /**
     * Debug mode
     */
    private boolean debug;
    /**
     * pixels per unit on the X axis
     */
    private float ppuX;
    /**
     * pixels per unit on the Y axis
     */
    private float ppuY;

    /**
     * Setter debug
     *
     * @return debug
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * Getter debug
     *
     * @param debug debug
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    /**
     * Set Size
     *
     * @param w weight
     * @param h height
     */
    public void setSize(int w, int h) {
        ppuX = (float) w / CAMERA_WIDTH;
        ppuY = (float) h / CAMERA_HEIGHT;
    }

    /**
     * Constructor WorldRender
     *
     * @param world World
     * @param debug debug
     */
    public WorldRenderer(World world, boolean debug) {
        this.world = world;
        this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
        this.cam.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
        this.cam.update();
        this.debug = debug;
        spriteBatch = new SpriteBatch();
        loadTextures();
    }

    /**
     * Set SpriteBatch
     */
    private void loadTextures() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("images/textures/textures.pack.atlas"));
        studentIdleLeft = atlas.findRegion("bob01");
        studentIdleRight = new TextureRegion(studentIdleLeft);
        studentIdleRight.flip(true, false);
        blockTexture = atlas.findRegion("block");
        markTexture = atlas.findRegion("block");
//        bonusTexture = atlas.findRegion("block");
        bonusTexture = new TextureRegion(new Texture("images/coin.png"));
        TextureRegion[] walkLeftFrames = new TextureRegion[5];

        for (int i = 0; i < 5; i++) {
            walkLeftFrames[i] = atlas.findRegion("bob0" + (i + 2));
        }
        walkLeftAnimation = new Animation<>(RUNNING_FRAME_DURATION, walkLeftFrames);

        TextureRegion[] walkRightFrames = new TextureRegion[5];

        for (int i = 0; i < 5; i++) {
            walkRightFrames[i] = new TextureRegion(walkLeftFrames[i]);
            walkRightFrames[i].flip(true, false);
        }

        walkRightAnimation = new Animation<>(RUNNING_FRAME_DURATION, walkRightFrames);
        studentJumpLeft = atlas.findRegion("bobup");
        studentJumpRight = new TextureRegion(studentJumpLeft);
        studentJumpRight.flip(true, false);
        studentFallLeft = atlas.findRegion("bobdown");
        studentFallRight = new TextureRegion(studentFallLeft);
        studentFallRight.flip(true, false);
    }

    /**
     * Render world
     */
    public void render() {
        spriteBatch.begin();
        drawBlocks();
        drawStudent();
        drawMark();
        drawBonus();
        spriteBatch.end();
        drawCollisionBlocks();
        if (debug) drawDebug();
    }

    /**
     * Draw blocks
     */
    private void drawBlocks() {
        for (Block block : world.getDrawableBlocks((int) CAMERA_WIDTH, (int) CAMERA_HEIGHT)) {
            spriteBatch.draw(blockTexture, block.getPosition().x * ppuX, block.getPosition().y * ppuY, Block.SIZE * ppuX, Block.SIZE * ppuY);
        }
    }

    /**
     * Draw mark
     */
    private void drawMark() {
        for (Mark mark : world.getDrawableMark((int) CAMERA_WIDTH, (int) CAMERA_HEIGHT)) {
            spriteBatch.draw(markTexture, (mark.getPosition().x + 0.125f) * ppuX, (mark.getPosition().y + 0.125f) * ppuY, Mark.SIZE * ppuX, Mark.SIZE * ppuY);
        }
    }

    /**
     * Draw bonus
     */
    private void drawBonus() {
        for (Bonus bonus : world.getDrawableBonus((int) CAMERA_WIDTH, (int) CAMERA_HEIGHT)) {
            if(world.getBonusIgnore().contains(bonus, false)){
                continue;
            }
            spriteBatch.draw(bonusTexture, (bonus.getPosition().x + 0.125f) * ppuX, (bonus.getPosition().y + 0.125f) * ppuY, Bonus.SIZE * ppuX, Bonus.SIZE * ppuY);
        }
    }

    /**
     * Draw student
     */
    private void drawStudent() {
        Student student = world.getStudent();
        TextureRegion studentFrame = student.isFacingLeft() ? studentIdleLeft : studentIdleRight;
        if (student.getState().equals(Student.State.WALKING)) {
            studentFrame = student.isFacingLeft() ? walkLeftAnimation.getKeyFrame(student.getStateTime(), true) : walkRightAnimation.getKeyFrame(student.getStateTime(), true);
        } else if (student.getState().equals(Student.State.JUMPING)) {
            if (student.getVelocity().y > 0) {
                studentFrame = student.isFacingLeft() ? studentJumpLeft : studentJumpRight;
            } else {
                studentFrame = student.isFacingLeft() ? studentFallLeft : studentFallRight;
            }
        }
        spriteBatch.draw(studentFrame, student.getPosition().x * ppuX, student.getPosition().y * ppuY, Student.SIZE * ppuX, Student.SIZE * ppuY);
    }

    /**
     * Visible collision block
     */
    private void drawCollisionBlocks() {
        debugRenderer.setProjectionMatrix(cam.combined);
        debugRenderer.begin(ShapeType.Filled);
        debugRenderer.setColor(new Color(1, 1, 1, 1));
        for (Rectangle rect : world.getCollisionRects()) {
            debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
        }
        debugRenderer.end();
    }

    /**
     * Debug mode
     */
    private void drawDebug() {
        debugRenderer.setProjectionMatrix(cam.combined);
        debugRenderer.begin(ShapeType.Filled);
        for (Block block : world.getDrawableBlocks((int) CAMERA_WIDTH, (int) CAMERA_HEIGHT)) {
            Rectangle rect = block.getBounds();
            debugRenderer.setColor(new Color(1, 0, 0, 1));
            debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
        }
        Student student = world.getStudent();
        Rectangle rect = student.getBounds();
        debugRenderer.setColor(new Color(0, 1, 0, 1));
        debugRenderer.rect(rect.x, rect.y, rect.width, rect.height);
        debugRenderer.end();
    }

}
