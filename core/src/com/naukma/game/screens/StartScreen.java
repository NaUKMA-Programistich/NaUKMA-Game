package com.naukma.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class StartScreen implements Screen {

    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    BitmapFont font;

    private Game game;
    public StartScreen(Game game){
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();

        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean keyDown (int keyCode) {

                if(keyCode == Input.Keys.SPACE){
                    game.setScreen(new GameScreen(game));
                }

                return true;
            }

        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, .25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        font.draw(batch, "Title Screen", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .75f);
        font.draw(batch, "Finish session to win.", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .5f);
        font.draw(batch, "Press space to play.", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .25f);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    }
}
