package com.badlogicgames.testgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogicgames.testgame.LevelGame.FirstLevel;
import org.w3c.dom.Text;

import java.nio.file.spi.FileTypeDetector;


public class PauseScreen implements Screen {
    private Main game;
    private OrthographicCamera gamecame;
    private Viewport gameport;
    private Texture pauseScreenImg;
    private Sprite pauseSprite;
    private Stage stage;

    // Resume button
    private Texture ResumeButtonImg;
    private Sprite ResumeSprite;
    private ImageButton Resume;

    // Resume Button

    private Texture RestartButton;
    private Sprite RestartSprite;
    private ImageButton Restart;

    // Menu Button
    private Texture MenuButton;
    private Sprite MenuSprite;
    private ImageButton Menu;

    // The Home Song Is On
    private Texture OnLevelSongImg;
    private Sprite OnLevelSongSprite;
    private ImageButton OnLevelSong;
    public static boolean SongIsOn = false;
    private static boolean SongIsOff = false;

    // The Home Song is Off
    private Texture OffLevelSongImg;
    private Sprite OffLevelSongSprite;
    private ImageButton OffLevelSong;




    public PauseScreen(Main game)
    {
        this.game = game;
        gamecame = new OrthographicCamera();
        gameport = new ScreenViewport(gamecame);



        pauseScreenImg = new Texture("PauseLevel1.jpg");
        pauseSprite = new Sprite(pauseScreenImg);
        pauseSprite.setPosition(0,0);
        stage = new Stage(gameport);
        Gdx.input.setInputProcessor(stage);
        ResumeButtonImg = new Texture("Resume.png");
        ResumeSprite = new Sprite(ResumeButtonImg);

        RestartButton = new Texture("Restart.png");
        RestartSprite = new Sprite(RestartButton);

        MenuButton = new Texture("MainBack.png");
        MenuSprite = new Sprite(MenuButton);

        OnLevelSongImg = new Texture("OnLevelMusicButton.png");
        OnLevelSongSprite = new Sprite(OnLevelSongImg);

        OffLevelSongImg = new Texture("OffLevelMusicButton.png");
        OffLevelSongSprite = new Sprite(OffLevelSongImg);

        TextureRegionDrawable OffHomeSongDrawable = new TextureRegionDrawable(OffLevelSongSprite);
        OffLevelSong = new ImageButton(OffHomeSongDrawable);

        TextureRegionDrawable ResumeDrawable = new TextureRegionDrawable(ResumeSprite);
        Resume = new ImageButton(ResumeDrawable);
        Resume.setPosition(20,gameport.getWorldHeight() - 179);
        Resume.setSize(150,150);

        TextureRegionDrawable RestartDrawable = new TextureRegionDrawable(RestartSprite);
        Restart = new ImageButton(RestartDrawable);

        Restart.setPosition(90,gameport.getWorldHeight() - 325);
        Restart.setSize(150,150);

        TextureRegionDrawable MenuDrawable = new TextureRegionDrawable(MenuSprite);
        Menu = new ImageButton(MenuDrawable);

        Menu.setPosition(90,gameport.getWorldHeight() - 480);
        Menu.setSize(150,150);

        Resume.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                if(FirstLevel.checkOnScreen())
                {
                    game.setScreen(new FirstLevel(game));
                }
            }
        });

        Restart.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                if(FirstLevel.checkOnScreen())
                {
                    game.setScreen(new FirstLevel(game));
                }
            }
        });

        Menu.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                SelectLevelScreen.LevelMusic.pause();
                game.setScreen(new SelectLevelScreen(game));
                GamePlayScreen.backgroundMusic.play();
            }
        });

        TextureRegionDrawable OnHomeSongDrawable = new TextureRegionDrawable(OnLevelSongSprite);
        OnLevelSong = new ImageButton(OnHomeSongDrawable);
        OnLevelSong.setSize(127,127);
        OnLevelSong.setPosition(20,40);

        OffLevelSong.setPosition(-125,-125);
        OffLevelSong.setSize(125,125);

        OnLevelSong.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                OffLevelSong.setPosition(20,40);
                OnLevelSong.setPosition(-125,-125);
                SelectLevelScreen.LevelMusic.pause();
            }
        });

        OffLevelSong.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                OnLevelSong.setPosition(20,40);
                OffLevelSong.setPosition(-125,-125);
                SelectLevelScreen.LevelMusic.play();
            }
        });

        stage.addActor(OffLevelSong);
        stage.addActor(OnLevelSong);


        stage.addActor(Menu);
        stage.addActor(Restart);
        stage.addActor(Resume);
    }
    @Override
    public void show() {
        gameport.apply();
    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(Color.BLACK);
        gameport.apply();
        gamecame.update();
        game.batch.setProjectionMatrix(gamecame.combined);
        game.batch.begin();

        game.batch.draw(pauseSprite,0,0,gameport.getWorldWidth(),gameport.getWorldHeight());
        game.batch.end();
        stage.act(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width,height,true);
        gamecame.position.set(gameport.getWorldWidth() / 2, gameport.getWorldHeight() / 2, 0);
        gamecame.update();
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
        // Dispose of all textures and resources used in this screen
        pauseScreenImg.dispose();
        ResumeButtonImg.dispose();
        RestartButton.dispose();
        MenuButton.dispose();
        OnLevelSongImg.dispose();
        OffLevelSongImg.dispose();

        // Dispose of the stage
        stage.dispose();
    }

}
