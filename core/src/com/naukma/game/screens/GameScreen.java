package com.naukma.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.naukma.game.controller.WorldController;
import com.naukma.game.entity.World;
import com.naukma.game.view.WorldRenderer;

import static com.naukma.game.Main.MUSIC_VOLUME;

/**
 * class GameScreen
 */
public class GameScreen implements Screen, InputProcessor {

    public static int firstPoints = 0;
    public static int secondPoints = 0;
    public static int thirdPoints = 6;
    public static int fourthPoints = 0;
    public static int fifthPoints = 100;
    public static int levelNumber = 1;
    public static boolean isNextLevel = false;
    /**
     * Game
     */
    private final Game game;
    Music gameMusic = Gdx.audio.newMusic(Gdx.files.internal("music/game.wav"));
    /**
     * WorldRenderer
     */
    private WorldRenderer renderer;
    /**
     * WorldController
     */
    private WorldController controller;
    /**
     * width, height
     */
    private int width, height;


    /**
     * Constructor GameScreen
     *
     * @param game Game
     */
    public GameScreen(Game game) {
        this.game = game;
    }

    public GameScreen(Game game, int levelNumber) {
        this.game = game;
        GameScreen.levelNumber = levelNumber;
    }

    public static void resetPoints() {
        firstPoints = 0;
        secondPoints = 0;
        thirdPoints = 6;
        fourthPoints = 0;
        fifthPoints = 0;
    }

    /**
     * Show Screen
     */
    @Override
    public void show() {
        startGameMusic();
        switchCameraSize();
        startWorld();
        Gdx.input.setInputProcessor(this);
    }

    private void startWorld() {
        World world = new World(levelNumber);
        renderer = new WorldRenderer(world, false);
        controller = new WorldController(world);
    }

    private void startGameMusic() {
        if (gameMusic.isPlaying()) {
            gameMusic.stop();
        }
        gameMusic.setLooping(true);
        gameMusic.setVolume(MUSIC_VOLUME);
        gameMusic.play();
    }

    public void switchCameraSize() {
        if (levelNumber == 1) {
            WorldRenderer.setCameraHeight(18f);
        } else {
            WorldRenderer.setCameraHeight(16f);
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

        controller.update(delta);
        renderer.render();

        checkNextLevel();
    }

    public void clearScreen() {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public void checkNextLevel() {
        if (isNextLevel) {
            stopMusic();
            if (levelNumber == 6) {
                gotoEndScreen();
                return;
            }
            isNextLevel = false;
            stopButtons();
            game.setScreen(new GameScreen(game, levelNumber));
        }
    }

    private void stopMusic() {
        gameMusic.setLooping(false);
        gameMusic.stop();
    }

    private void stopButtons() {
        controller.leftReleased();
        controller.rightReleased();
        controller.jumpReleased();
        controller.fireReleased();
    }

    private void gotoEndScreen() {
        isNextLevel = false;
        levelNumber = 1;
        game.setScreen(new EndScreen(game));
    }

    /**
     * resize
     *
     * @param width  width
     * @param height height
     */
    @Override
    public void resize(int width, int height) {
        renderer.setSize(width, height);
        this.width = width;
        this.height = height;
    }

    /**
     * Pause Screen
     */
    @Override
    public void pause() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void resume() {
        Gdx.input.setInputProcessor(this);
    }

    /**
     * Hide Screen
     */
    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    /**
     * Exit Screen
     */
    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
    }

    /**
     * keyDown
     *
     * @param keycode int
     * @return boolean
     */
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.A)
            controller.leftPressed();
        if (keycode == Input.Keys.D)
            controller.rightPressed();
        if (keycode == Input.Keys.SPACE)
            controller.jumpPressed();
        if (keycode == Input.Keys.X)
            controller.firePressed();
        if (keycode == Input.Keys.ESCAPE)
            Gdx.app.exit();
        if (keycode == Input.Keys.ENTER) {
            gameMusic.stop();
            stopButtons();
            levelNumber = 1;
            game.setScreen(new StartScreen(game));
        }
        return true;
    }

    /**
     * keyUp
     *
     * @param keycode int
     * @return boolean
     */
    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.A)
            controller.leftReleased();
        if (keycode == Input.Keys.D)
            controller.rightReleased();
        if (keycode == Input.Keys.SPACE)
            controller.jumpReleased();
        if (keycode == Input.Keys.X)
            controller.fireReleased();
        if (keycode == Input.Keys.Q)
            renderer.setDebug(!renderer.isDebug());
        return true;

    }

    /**
     * keyTyped
     *
     * @param character char
     * @return boolean
     */
    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    /**
     * touchDown
     *
     * @param x       x
     * @param y       y
     * @param pointer pointer
     * @param button  button
     * @return boolean
     */
    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        if (x < width / 2 && y > height / 2) {
            controller.leftPressed();
        }
        if (x > width / 2 && y > height / 2) {
            controller.rightPressed();
        }
        return true;
    }

    /**
     * touchUp
     *
     * @param x       x
     * @param y       y
     * @param pointer pointer
     * @param button  button
     * @return boolean
     */
    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        if (x < width / 2 && y > height / 2) {
            controller.leftReleased();
        }
        if (x > width / 2 && y > height / 2) {
            controller.rightReleased();
        }
        return true;
    }

    /**
     * touchDragged
     *
     * @param x       x
     * @param y       y
     * @param pointer pointer
     * @return boolean
     */
    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        return false;
    }

    /**
     * mouseMoved
     *
     * @param screenX screenX
     * @param screenY screenY
     * @return boolean
     */
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    /**
     * scrolled
     *
     * @param amountX amountX
     * @param amountY amountY
     * @return boolean
     */
    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

}
