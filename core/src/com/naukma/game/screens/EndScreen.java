package com.naukma.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

import static com.naukma.game.Main.MUSIC_VOLUME;

/**
 * class EndScreen
 */
public class EndScreen implements Screen {

    /**
     * Game
     */
    private final Game game;
    /**
     * ArrayList results
     */
    private final ArrayList<String> results = new ArrayList<>();
    /**
     * SpriteBatch
     */
    private SpriteBatch batch;
    /**
     * BitmapFont
     */
    private BitmapFont font;

    Music endMusic = Gdx.audio.newMusic(Gdx.files.internal("music/end.wav"));


    /**
     * Constructor EndScreen
     *
     * @param game Game
     */
    public EndScreen(Game game) {
        this.game = game;
        ArrayList<Integer> points = createPointsList();
        GameScreen.resetPoints();
        createResults(points);
    }

    private ArrayList<Integer> createPointsList(){
        ArrayList<Integer> points = new ArrayList<>();
        points.add(GameScreen.firstPoints);
        points.add(GameScreen.secondPoints);
        points.add(GameScreen.thirdPoints);
        points.add(GameScreen.fourthPoints);
        if(GameScreen.fifthPoints > 100){
            GameScreen.fifthPoints = 100;
        }
        points.add(GameScreen.fifthPoints);
        return points;
    }

    private void createResults(ArrayList<Integer> points){

        for (int i = 0; i < 5; i++) {
            String nextResult = "For the subject number " + (i + 1) + " you got " + points.get(i) + " points. ";

            if (points.get(i) < 60) {
                nextResult += "Your grade is F, bad job!";
            } else if (points.get(i) < 70) {
                nextResult += "Your grade is D, not very good job!";
            } else if (points.get(i) < 80) {
                nextResult += "Your grade is C, OK job!";
            } else if (points.get(i) < 90) {
                nextResult += "Your grade is B, good job!";
            } else if (points.get(i) < 100) {
                nextResult += "Your grade is A, great job!";
            } else if (points.get(i) == 100) {
                nextResult += "Your grade is A+, excellent job!";
            }
            results.add(nextResult);
        }
    }


    /**
     * Show Screen
     */
    @Override
    public void show() {
        setupMusic();
        setupDraw();
        setupInput();
    }

    private void setupMusic(){
        endMusic.setLooping(true);
        endMusic.setVolume(MUSIC_VOLUME);
        endMusic.play();
    }

    private void setupDraw(){
        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    private void setupInput(){
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.ENTER) {
                    endMusic.setLooping(false);
                    endMusic.stop();
                    game.setScreen(new StartScreen(game));
                }

                if (keyCode == Input.Keys.ESCAPE) {
                    endMusic.setLooping(false);
                    endMusic.stop();
                    Gdx.app.exit();
                }

                return true;
            }

        });
    }

    /**
     * Render Screen
     *
     * @param delta time
     */
    @Override
    public void render(float delta) {
        clearScreen();
        drawResults();
    }

    private void clearScreen(){
        Color skyColor = Color.SKY;
        Gdx.gl.glClearColor(skyColor.r, skyColor.g, skyColor.b, skyColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void drawResults(){
        Texture texture = new Texture(Gdx.files.internal("fonts/test8.png"), true); // true enables mipmaps
        texture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear); // linear filtering in nearest mipmap image

        font = new BitmapFont(Gdx.files.internal("fonts/test8.fnt"), new TextureRegion(texture), false);
        font.getData().setScale(0.7f, 0.7f);

        Texture glyba01 = new Texture(Gdx.files.internal("images/glyba01.jpg"));

        batch.begin();
        batch.enableBlending();
        batch.draw(glyba01, Gdx.graphics.getWidth()* .8f,Gdx.graphics.getHeight() * .5f, 300, 300);
        font.draw(batch, "Session ended", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .9f);
        font.draw(batch, results.get(0), Gdx.graphics.getWidth() * .05f, Gdx.graphics.getHeight() * .8f);
        font.draw(batch, results.get(1), Gdx.graphics.getWidth() * .05f, Gdx.graphics.getHeight() * .7f);
        font.draw(batch, results.get(2), Gdx.graphics.getWidth() * .05f, Gdx.graphics.getHeight() * .6f);
        font.draw(batch, results.get(3), Gdx.graphics.getWidth() * .05f, Gdx.graphics.getHeight() * .5f);
        font.draw(batch, results.get(4), Gdx.graphics.getWidth() * .05f, Gdx.graphics.getHeight() * .4f);
        font.draw(batch, "Press Enter to restart the game, Esc to Quit the game", Gdx.graphics.getWidth() * .05f, Gdx.graphics.getHeight() * .1f);
        batch.disableBlending();
        batch.end();
    }

    /**
     * Resize Screen
     *
     * @param width  width
     * @param height height
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     * Pause Screen
     */
    @Override
    public void pause() {

    }

    /**
     * Resume Screen
     */
    @Override
    public void resume() {

    }

    /**
     * Hide Screen
     */
    @Override
    public void hide() {

    }

    /**
     * Exit Screen
     */
    @Override
    public void dispose() {

    }
}
