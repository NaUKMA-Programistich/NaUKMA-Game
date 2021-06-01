package com.naukma.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class EndScreen implements Screen {
    private Game game;

    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    BitmapFont font;

    private ArrayList<Integer> points = new ArrayList<>();
    private ArrayList<String> results = new ArrayList<>();

    public EndScreen(Game game) {
        this.game = game;
        points.clear();
        points.add(100);
        points.add(89);
        points.add(79);
        points.add(69);
        points.add(59);

        results.clear();
        for (int i = 0; i < 5; i++) {
            String nextResult = "For the subject number " + i + " you got " + points.get(i) + " points. ";

            if (points.get(i) < 60) {
                nextResult += "Your grade is F, bad job!";
            } else if(points.get(i) <70){
                nextResult += "Your grade is D, not very good job!";
            } else if(points.get(i) <80){
                nextResult += "Your grade is C, OK job!";
            } else if(points.get(i) < 90){
                nextResult += "Your grade is B, good job!";
            } else if(points.get(i) < 100){
                nextResult += "Your grade is A, great job!";
            } else if(points.get(i) == 100){
                nextResult += "Your grade is A+, excellent job!";
            }
            results.add(nextResult);
        }
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();

        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean keyDown (int keyCode) {

                if(keyCode == Input.Keys.ENTER){
                    game.setScreen(new StartScreen(game));
                }

                if(keyCode == Input.Keys.ESCAPE){
                    Gdx.app.exit();
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
        font.draw(batch, "Win Screen", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .9f);
        font.draw(batch, results.get(0), Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .8f);
        font.draw(batch, results.get(1), Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .7f);
        font.draw(batch, results.get(2), Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .6f);
        font.draw(batch, results.get(3), Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .5f);
        font.draw(batch, results.get(4), Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .4f);
        font.draw(batch, "Press Enter to restart the game, Esc to quit the game", Gdx.graphics.getWidth() * .75f, Gdx.graphics.getHeight() * .7f);
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
