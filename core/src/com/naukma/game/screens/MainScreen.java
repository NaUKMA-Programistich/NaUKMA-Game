package com.naukma.game.screens;

import com.badlogic.gdx.*;

/**
 * class MainScreen
 */
public class MainScreen implements Screen {

    /**
     * Game
     */
    private final Game game;
    /**
     * currentScreen
     */
    private Screens currentScreen = Screens.TITLE;

    /**
     * Constructor MainScreen
     *
     * @param game Game
     */
    public MainScreen(Game game) {
        this.game = game;
    }

    /**
     * Show Screen
     */
    @Override
    public void show() {
        setMainInputProcessor();
    }

    /**
     * setMainInputProcessor
     */
    private void setMainInputProcessor(){
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean keyDown(int keyCode) {

                if (currentScreen == Screens.TITLE && keyCode == Input.Keys.SPACE) {
                    currentScreen = Screens.MAIN_GAME;
                } else if (currentScreen == Screens.GAME_OVER && keyCode == Input.Keys.ENTER) {
                    GameScreen.resetPoints();
                    currentScreen = Screens.TITLE;
                }

                return true;
            }

            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {
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
        if (currentScreen == Screens.TITLE) {
            game.setScreen(new StartScreen(game));
        } else if (currentScreen == Screens.MAIN_GAME) {
            game.setScreen(new GameScreen(game));
        } else if (currentScreen == Screens.GAME_OVER) {
            game.setScreen(new EndScreen(game));
        }
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


    /**
     * Screens
     */
    enum Screens {
        TITLE,
        MAIN_GAME,
        GAME_OVER
    }
}
