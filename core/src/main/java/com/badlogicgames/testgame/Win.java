package com.badlogicgames.testgame;

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
import com.badlogic.gdx.utils.Select;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogicgames.testgame.LevelGame.FirstLevel;
import com.badlogicgames.testgame.Main;
import com.badlogicgames.testgame.PauseScreen;
import org.w3c.dom.Text;


public class Win implements Screen {
    private Main game;
    private Texture backgroundtexture;
    private OrthographicCamera gamecame;
    private Viewport gameport;
    private Texture RestartButton;
    private Sprite RestartSprite;
    private ImageButton Restart;

    private Texture SelectLevelButton;
    private Sprite SelectLevelSprite;
    private ImageButton SelectLevel;

    private Texture NextLevelButton;
    private Sprite NextLevelSprite;
    private ImageButton NextLevel;

    private Stage stage;
    private static boolean OnScreen = false;
    public Win(Main game) {
        this.game = game;
        OnScreen = true;
        backgroundtexture = new Texture("LevelPassed.jpg");
        gamecame = new OrthographicCamera();
        gameport = new ScreenViewport(gamecame);
        stage = new Stage(gameport);
        Gdx.input.setInputProcessor(stage);

        RestartButton = new Texture("Restart.png");
        RestartSprite = new Sprite(RestartButton);

        SelectLevelButton = new Texture("MainBack.png");
        SelectLevelSprite = new Sprite(SelectLevelButton);

        NextLevelButton = new Texture("NextLevel.png");
        NextLevelSprite = new Sprite(NextLevelButton);

        TextureRegionDrawable NextLevelDrawable = new TextureRegionDrawable(NextLevelSprite);
        NextLevel = new ImageButton(NextLevelDrawable);

        NextLevel.setSize(190,300);
        NextLevel.setPosition(gameport.getWorldWidth()/2 + 120, 10);

        TextureRegionDrawable LevelDrawable = new TextureRegionDrawable(SelectLevelSprite);
        SelectLevel = new ImageButton(LevelDrawable);
        SelectLevel.setSize(190,300);
        SelectLevel.setPosition(gameport.getWorldWidth()/2 - 300, 0);

        SelectLevel.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                game.setScreen(new SelectLevelScreen(game));
            }
        });


        TextureRegionDrawable  RestartDrawable = new TextureRegionDrawable(RestartSprite);
        Restart = new ImageButton(RestartDrawable);
        Restart.setSize(180,180);
        Restart.setPosition(gameport.getWorldWidth()/2 - 80 , 60);
        Restart.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                game.setScreen(new FirstLevel(game));
                SelectLevelScreen.LevelMusic.play();
                GamePlayScreen.backgroundMusic.pause();
            }
        });
        stage.addActor(NextLevel);
        stage.addActor(SelectLevel);
        stage.addActor(Restart);
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
        // Dispose of all textures
        backgroundtexture.dispose();
        RestartButton.dispose();
        SelectLevelButton.dispose();

        // Dispose of the stage
        stage.dispose();
    }

}

