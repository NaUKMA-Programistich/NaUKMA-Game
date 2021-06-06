package com.naukma.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.naukma.game.utils.GifDecoder;

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
     * catJam
     */
    Animation<TextureRegion> catJam;
    /**
     * elapsed
     */
    float elapsed;

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
        catJam = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("gifs/catJam.gif").read());
    }

    /**
     * startMenuMusic
     */
    private void startMenuMusic() {
        menuMusic.setLooping(true);
        menuMusic.setVolume(MUSIC_VOLUME);
        menuMusic.play();
    }

    /**
     * setupDraw
     */
    private void setupDraw() {
        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    /**
     * setupStartProcessor
     */
    private void setupStartProcessor() {
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

    /**
     * stopMusic
     */
    private void stopMusic() {
        menuMusic.setLooping(false);
        menuMusic.stop();
    }

    /**
     * switchScreen
     * @param keyCode int
     */
    private void switchScreen(int keyCode) {
        switch (keyCode) {
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
            case Input.Keys.ESCAPE:
                Gdx.app.exit();
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
        elapsed += Gdx.graphics.getDeltaTime();
        clearScreen();

        basicDraw();
    }

    /**
     * clearScreen
     */
    private void clearScreen() {
        //Gdx.gl.glClearColor(0, 0, 2f, 1);
        Color skyColor = Color.SKY;
        Gdx.gl.glClearColor(skyColor.r, skyColor.g, skyColor.b, skyColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    /**
     * basicDraw
     */
    private void basicDraw() {
        Texture texture = new Texture(Gdx.files.internal("fonts/test4.5.png"), true); // true enables mipmaps
        texture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear); // linear filtering in nearest mipmap image

        font = new BitmapFont(Gdx.files.internal("fonts/test4.5.fnt"), new TextureRegion(texture), false);

        batch.begin();
        //font.getData().setScale(5, 5);
        batch.draw(catJam.getKeyFrame(elapsed), Gdx.graphics.getWidth() * 0.8f, Gdx.graphics.getHeight() * 0.5f);
        font.draw(batch, "Session day", Gdx.graphics.getWidth() * .1f, Gdx.graphics.getHeight() * .9f);
        font.draw(batch, "You're a student and you need to collect points", Gdx.graphics.getWidth() * .1f, Gdx.graphics.getHeight() * .75f);
        font.draw(batch, "1 Coin - 6 points, Red coin - 40 points and next level", Gdx.graphics.getWidth() * .1f, Gdx.graphics.getHeight() * .65f);
        font.draw(batch, "Press 'A' and 'D' to move left or right", Gdx.graphics.getWidth() * .1f, Gdx.graphics.getHeight() * .5f);
        font.draw(batch, "Press 'Space' to jump", Gdx.graphics.getWidth() * .1f, Gdx.graphics.getHeight() * .4f);
        font.draw(batch, "Finish session to win", Gdx.graphics.getWidth() * .1f, Gdx.graphics.getHeight() * .3f);
        font.draw(batch, "by #define Failure us.", Gdx.graphics.getWidth() * .1f, Gdx.graphics.getHeight() * .1f);
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
