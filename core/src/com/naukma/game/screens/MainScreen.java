package com.naukma.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.naukma.game.Main;

public class MainScreen implements Screen {

    private Game game;
    public MainScreen(Game game){
        this.game = game;
    }

    enum Screens{
        TITLE,
        MAIN_GAME,
        GAME_OVER
    }

    Screens currentScreen = Screens.TITLE;

    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    BitmapFont font;

    @Override
    public void show() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();

        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean keyDown (int keyCode) {

                if(currentScreen == Screens.TITLE && keyCode == Input.Keys.SPACE){
                    currentScreen = Screens.MAIN_GAME;
                }
                else if(currentScreen == Screens.GAME_OVER && keyCode == Input.Keys.ENTER){
                    currentScreen = Screens.TITLE;
                }

                return true;
            }

            @Override
            public boolean touchDown (int x, int y, int pointer, int button) {
                if(currentScreen == Screens.MAIN_GAME){


                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta){
        if(currentScreen == Screens.TITLE){
//            Gdx.gl.glClearColor(0, .25f, 0, 1);
//            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//            batch.begin();
//            font.draw(batch, "Title Screen", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .75f);
//            font.draw(batch, "Finish session to win.", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .5f);
//            font.draw(batch, "Press space to play.", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .25f);
//            batch.end();
            game.setScreen(new StartScreen(game));
        }
        else if(currentScreen == Screens.MAIN_GAME) {
            // Gdx.gl.glClearColor(0, 0, .25f, 1);
            //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            game.setScreen(new GameScreen(game));
        }
        else if(currentScreen == Screens.GAME_OVER){
//            Gdx.gl.glClearColor(.25f, 0, 0, 1);
//            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//            batch.begin();
//            font.draw(batch, "You win!", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .75f);
//            font.draw(batch, "Press enter to restart.", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .25f);
//            batch.end();
            game.setScreen(new EndScreen(game));
        }
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

    public Screens getCurrentScreen(){
        return currentScreen;
    }
}
