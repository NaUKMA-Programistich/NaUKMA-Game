package com.naukma.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
        batch = new SpriteBatch();
        font = new BitmapFont();
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.SPACE)
                    game.setScreen(new GameScreen(game));
                if (keyCode == Input.Keys.NUM_1)
                    game.setScreen(new GameScreen(game, 1));
                if (keyCode == Input.Keys.NUM_2)
                    game.setScreen(new GameScreen(game, 2));
                if (keyCode == Input.Keys.NUM_3)
                    game.setScreen(new GameScreen(game, 3));
                if (keyCode == Input.Keys.NUM_4)
                    game.setScreen(new GameScreen(game, 4));
                if (keyCode == Input.Keys.NUM_5)
                    game.setScreen(new GameScreen(game, 5));
                if(keyCode == Input.Keys.END)
                    game.setScreen(new EndScreen(game));
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
        Gdx.gl.glClearColor(0, .25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
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
