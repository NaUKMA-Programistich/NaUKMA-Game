package com.naukma.game;

import com.badlogic.gdx.Game;
import com.naukma.game.screens.GameScreen;
import com.naukma.game.screens.StartScreen;

/**
 * Main class
 */
public class Main extends Game {


    /**
     * Create
     */
    @Override
    public void create() {
        //setScreen(new StartScreen());
        setScreen(new GameScreen());
    }
}
