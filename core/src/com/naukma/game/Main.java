package com.naukma.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.naukma.game.screens.GameScreen;
import com.naukma.game.screens.MainScreen;
import com.naukma.game.screens.StartScreen;

/**
 * Main class
 */
public class Main extends Game {

    private Game game;

    public Main(){
        game = this;
    }

    /**
     * Create
     */
    @Override
    public void create() {
        game.setScreen(new MainScreen(game));
    }

    @Override
    public void render(){
        clearBlack();
        super.render();
    }


    public void clearBlack(){
        Gdx.gl.glClearColor(0, 0, 0, 0);
    }

    @Override
    public void dispose(){

    }
}
