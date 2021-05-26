package com.naukma.game.worlds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.naukma.game.entity.Block;
import com.naukma.game.entity.Student;


public class WorldRenderer {

    private final World world;
    private static final float CAMERA_WIDTH = 10f;
    private static final float CAMERA_HEIGHT = 7f;

    /**
     * Textures
     **/
    private Texture studentTexture;
    private Texture blockTexture;

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
        studentTexture = new Texture(Gdx.files.internal("images/bob_01.png"));
        blockTexture = new Texture(Gdx.files.internal("images/block.png"));

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
        Student stud = world.getStudent();
        spriteBatch.draw(studentTexture, stud.getPosition().x * ppuX, stud.getPosition().y * ppuY, Student.SIZE * ppuX, Student.SIZE * ppuY);

    }
}
