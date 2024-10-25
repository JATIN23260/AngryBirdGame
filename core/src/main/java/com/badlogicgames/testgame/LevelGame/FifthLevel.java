package com.badlogicgames.testgame.LevelGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogicgames.testgame.Main;
import com.badlogicgames.testgame.PauseScreen;


public class FifthLevel implements Screen {
    private Main game;
    private Texture backgroundtexture;
    private OrthographicCamera gamecame;
    private Viewport gameport;
    private Texture pauseButton;
    private Sprite PauseSprite;
    private ImageButton pause;
    private Stage stage;
    private static boolean OnScreen = false;
    public FifthLevel(Main game) {
        this.game = game;
        OnScreen = true;
        backgroundtexture = new Texture("LevelBackground.jpg");
        gamecame = new OrthographicCamera();
        gameport = new ScreenViewport(gamecame);
        stage = new Stage(gameport);
        Gdx.input.setInputProcessor(stage);

        pauseButton = new Texture("PauseButton.png");
        PauseSprite = new Sprite(pauseButton);
        TextureRegionDrawable  pauseDrawable = new TextureRegionDrawable(PauseSprite);
        pause = new ImageButton(pauseDrawable);
        pause.setSize(170,170);
        pause.setPosition(20,gameport.getWorldHeight()-190);
        pause.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                game.setScreen(new PauseScreen(game));
            }
        });
        stage.addActor(pause);
    }
    public static boolean checkOnScreen()
    {
        return OnScreen;
    }
    @Override
    public void show() {
        gameport.apply();
    }

    @Override
    public void render(float delta) {
        input();
        draw();
        stage.act(delta); // Update the stage with time delta
        stage.draw();
    }

    private void input() {

    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        gameport.apply();
        gamecame.update();
        game.batch.setProjectionMatrix(gamecame.combined);

        game.batch.begin();
        game.batch.draw(backgroundtexture, 0, 0, gameport.getWorldWidth(), gameport.getWorldHeight());
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {

        gameport.update(width, height, true);


        gamecame.position.set(gameport.getWorldWidth() / 2, gameport.getWorldHeight() / 2, 0);
        gamecame.update();
    }

    @Override
    public void pause() {
        // Handle pause logic
    }

    @Override
    public void resume() {
        // Handle resume logic
    }

    @Override
    public void hide() {
        // Handle when the screen is hidden
    }

    @Override
    public void dispose() {
        // Dispose of resources properly
        backgroundtexture.dispose();
    }
}
