package com.naukma.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.naukma.game.screens.MainScreen;

/**
 * Main class configuration
 */
public class Main extends Game {

    /**
     * Game
     */
    private final Game game;

    /**
     * MUSIC_VOLUME
     */
    public static final float MUSIC_VOLUME = 0.1f;

    /**
     * Constructor
     */
    public Main() {
        game = this;
    }

    /**
     * Create
     */
    @Override
    public void create() {
        game.setScreen(new MainScreen(game));
    }

    /**
     * Render screen
     */
    @Override
    public void render() {
        clearBlack();
        super.render();
    }

    /**
     * Clear screen
     */
    public void clearBlack() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
    }

    /**
     * Errors
     */
    @Override
    public void dispose() {

    }
}
