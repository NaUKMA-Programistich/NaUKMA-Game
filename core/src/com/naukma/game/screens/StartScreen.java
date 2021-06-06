package com.naukma.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.naukma.game.Main.MUSIC_VOLUME;

/**
 * class StartScreen
 */
public class StartScreen implements Screen {

    /**
     * Game game
     */
    private final Game game;
    /**
     * SpriteBatch batch
     */
    private SpriteBatch batch;
    /**
     * BitmapFont font
     */
    private BitmapFont font;
    /**
     *
     */
    Music menuMusic = Gdx.audio.newMusic(Gdx.files.internal("music/start.wav"));

    /**
     * Constructor StartScreen
     *
     * @param game Game
     */
    public StartScreen(Game game) {
        this.game = game;
    }

    /**
     * Show Screen
     */
    @Override
    public void show() {
        startMenuMusic();
        setupDraw();
        setupStartProcessor();
    }

    private void startMenuMusic(){
        menuMusic.setLooping(true);
        menuMusic.setVolume(MUSIC_VOLUME);
        menuMusic.play();
    }

    private void setupDraw(){
        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    private void setupStartProcessor(){
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.SPACE || keyCode == Input.Keys.NUM_1 || keyCode == Input.Keys.NUM_2 || keyCode == Input.Keys.NUM_3
                         || keyCode == Input.Keys.NUM_4 || keyCode == Input.Keys.NUM_5 || keyCode == Input.Keys.END) {
                    stopMusic();
                    switchScreen(keyCode);
                }
                return true;
            }
        });
    }

    private void stopMusic(){
        menuMusic.setLooping(false);
        menuMusic.stop();
    }

    private void switchScreen(int keyCode){
        switch (keyCode){
            case Input.Keys.SPACE:
                game.setScreen(new GameScreen(game));
                break;
            case Input.Keys.NUM_1:
                game.setScreen(new GameScreen(game, 1));
                break;
            case Input.Keys.NUM_2:
                game.setScreen(new GameScreen(game, 2));
                break;
            case Input.Keys.NUM_3:
                game.setScreen(new GameScreen(game, 3));
                break;
            case Input.Keys.NUM_4:
                game.setScreen(new GameScreen(game, 4));
                break;
            case Input.Keys.NUM_5:
                game.setScreen(new GameScreen(game, 5));
                break;
            case Input.Keys.END:
                game.setScreen(new EndScreen(game));
                break;
        }
    }

    /**
     * Render Screen
     *
     * @param delta time
     */
    @Override
    public void render(float delta) {
        clearScreen();
        basicDraw();
    }

    private void clearScreen(){
        Gdx.gl.glClearColor(0, .25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void basicDraw(){
        batch.begin();
        font.getData().setScale(5, 5);
        font.draw(batch, "Title Screen", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .75f);
        font.draw(batch, "Finish session to win.", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .5f);
        font.draw(batch, "Press space to play.", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .25f);
        batch.end();
    }

    /**
     * resize Screen
     *
     * @param width  width
     * @param height height
     */
    @Override
    public void resize(int width, int height) {

    }

    /**
     * Pause screen
     */
    @Override
    public void pause() {

    }

    /**
     * Resume screen
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
