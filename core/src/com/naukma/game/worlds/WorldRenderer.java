package com.naukma.game.worlds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.naukma.game.entity.Block;
import com.naukma.game.entity.Student;


public class WorldRenderer {

    private final World world;
    private static final float CAMERA_WIDTH = 10f;
    private static final float CAMERA_HEIGHT = 7f;

    private static final float RUNNING_FRAME_DURATION = 0.06f;

    /**
     * Textures
     **/
    private Texture studentTexture;
    private TextureRegion studentIdleLeft;
    private TextureRegion studentIdleRight;
    private TextureRegion blockTexture;
    private TextureRegion studentFrame;

    /**
     * Animations
     **/
    private Animation<? extends TextureRegion> walkLeftAnimation;
    private Animation<? extends TextureRegion> walkRightAnimation;

    private final SpriteBatch spriteBatch;
    private float ppuX; // pixels per unit on the X axis
    private float ppuY; // pixels per unit on the Y axis

    private int width;
    private int height;

    public void setSize(int w, int h) {
        width = w;
        height = h;

        ppuX = (float) w / CAMERA_WIDTH;
        ppuY = (float) h / CAMERA_HEIGHT;
    }


    public WorldRenderer(World world) {
        this.world = world;
        OrthographicCamera camera = new OrthographicCamera(10, 7);
        camera.position.set(5, 3.5f, 0);
        camera.update();
        spriteBatch = new SpriteBatch();
        loadTextures();
    }

    private void loadTextures() {
        studentTexture = new Texture(Gdx.files.internal("images/bob01.png"));

        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("/home/programistich/Projects/Game/core/assets/images/textures/textures.pack.atlas"));
        studentIdleLeft = atlas.findRegion("bob01");
        studentIdleRight = new TextureRegion(studentIdleLeft);
        studentIdleRight.flip(true, false);
        blockTexture = atlas.findRegion("block");
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
    }


    public void render() {
        spriteBatch.begin();
        drawBlocks();
        drawStudent();
        spriteBatch.end();
    }

    private void drawBlocks() {
        for (Block block : world.getBlocks()) {
            spriteBatch.draw(blockTexture, block.getPosition().x * ppuX, block.getPosition().y * ppuY, Block.SIZE * ppuX, Block.SIZE * ppuY);
        }
    }

    private void drawStudent() {
        Student student = world.getStudent();
        studentFrame = student.isFacingLeft() ? studentIdleLeft : studentIdleRight;
        if(student.getState().equals(Student.State.WALKING)) {
            if(student.isFacingLeft()){
                studentFrame = walkLeftAnimation.getKeyFrame(student.getStateTime(), true);
            }
            else{
                studentFrame = walkRightAnimation.getKeyFrame(student.getStateTime(), true);
            }
        }
        spriteBatch.draw(studentFrame, student.getPosition().x * ppuX, student.getPosition().y * ppuY, Student.SIZE * ppuX, Student.SIZE * ppuY);

    }


}
