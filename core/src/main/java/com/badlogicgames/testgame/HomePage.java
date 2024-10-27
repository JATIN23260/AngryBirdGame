package com.badlogicgames.testgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.w3c.dom.Text;

public class HomePage implements Screen {
    private Main game;
    private OrthographicCamera gamecame;
    private Viewport gameport;
    private Texture img;
    private Sprite sprite;
    private Texture Select_level_Image;
    private Stage stage;
    private ImageButton levelButton;
    private Sprite Levelsprite;

    private Texture ExitButtonimg;
    private ImageButton ExitButton;
    private Sprite Exitsprite;

    private Texture ReloadGameimg;
    private ImageButton loadbutton;
    private Sprite ReloadButton;

    private Texture backgroundbutton;
    private Sprite spritebutton;

    // The Home Song Is On
    private Texture OnHomeSongImg;
    private Sprite OnHomeSongSprite;
    private ImageButton OnHomeSong;
    private static boolean SongIsOn = false;
    private static boolean SongIsOff = false;

    // The Home Song is Off
    private Texture OffHomeSongImg;
    private Sprite OffHomeSongSprite;
    private ImageButton OffHomeSong;

    public HomePage(Main game)
    {
        this.game = game;
        img = new Texture("HomeImage.jpg");
        sprite = new Sprite(img);
        sprite.setPosition(0,0);
        gamecame = new OrthographicCamera();
        gameport = new ScreenViewport(gamecame);
        stage = new Stage(gameport);
        Gdx.input.setInputProcessor(stage);
        Select_level_Image = new Texture("Select_level.png");
        Levelsprite = new Sprite(Select_level_Image);
        ExitButtonimg = new Texture("Exit.png");
        Exitsprite = new Sprite(ExitButtonimg);

        OnHomeSongImg = new Texture("FullSongScreen.png");
        OnHomeSongSprite = new Sprite(OnHomeSongImg);


        backgroundbutton = new Texture("Backgroundbutton.png");
        spritebutton = new Sprite(backgroundbutton);
        spritebutton.setPosition(Gdx.graphics.getWidth()/2f - spritebutton.getWidth()/2f,
            Gdx.graphics.getHeight()/2f - spritebutton.getHeight()/2f);

        ReloadGameimg = new Texture("ReloadGameImage.png");
        ReloadButton = new Sprite(ReloadGameimg);

        OffHomeSongImg = new Texture("OffFullSongScreen.png");
        OffHomeSongSprite = new Sprite(OffHomeSongImg);

        TextureRegionDrawable ReloadDrawable = new TextureRegionDrawable(ReloadButton);
        loadbutton = new ImageButton(ReloadDrawable);
        loadbutton.setSize(100,100);
        loadbutton.setPosition(30,gameport.getWorldHeight() - 150);

        TextureRegionDrawable OffHomeSongDrawable = new TextureRegionDrawable(OffHomeSongSprite);
        OffHomeSong = new ImageButton(OffHomeSongDrawable);

//        loadbutton.addListener(new ClickListener()
//        {
//            @Override
//            public void clicked(InputEvent event, float x, float y)
//            {
//
//            }
//        });


        TextureRegionDrawable ExitDrawable = new TextureRegionDrawable(Exitsprite);
        ExitButton = new ImageButton(ExitDrawable);
        ExitButton.setPosition(9,35);
        ExitButton.setSize(150,150);

        ExitButton.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                Gdx.app.exit();
            }
        });


        TextureRegionDrawable selectLevelDrawable = new TextureRegionDrawable(Levelsprite);
        levelButton = new ImageButton(selectLevelDrawable);
        levelButton.setSize(360,360);
        levelButton.setPosition(gameport.getWorldWidth()/2 - levelButton.getWidth()/2 - 2,gameport.getWorldHeight()/2 - levelButton.getWidth()/2 - 80);

        levelButton.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                game.setScreen(new SelectLevelScreen(game));
            }
        });

        TextureRegionDrawable OnHomeSongDrawable = new TextureRegionDrawable(OnHomeSongSprite);
        OnHomeSong = new ImageButton(OnHomeSongDrawable);

        OnHomeSong.setPosition(150,42);
        OnHomeSong.setSize(127,127);
        OffHomeSong.setPosition(-125,-125);
        OffHomeSong.setSize(125,125);

        OnHomeSong.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                OffHomeSong.setPosition(150,42);
                OnHomeSong.setPosition(-125,-125);
                GamePlayScreen.backgroundMusic.pause();
            }
        });

        OffHomeSong.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                OnHomeSong.setPosition(150,42);
                OffHomeSong.setPosition(-125,-125);
                GamePlayScreen.backgroundMusic.play();
            }
        });

        stage.addActor(OffHomeSong);
        stage.addActor(OnHomeSong);

        stage.addActor(loadbutton);
        stage.addActor(ExitButton);
        stage.addActor(levelButton);
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

        game.batch.draw(sprite, 0, 0, gameport.getWorldWidth(), gameport.getWorldHeight());
        game.batch.draw(spritebutton,spritebutton.getX(),spritebutton.getY());
        game.batch.end();
        stage.act(delta); // Update the stage with time delta
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width, height, true);


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
        // Dispose of the stage and all actors within it
        stage.dispose();

        // Dispose of all textures
        img.dispose();
        Select_level_Image.dispose();
        ExitButtonimg.dispose();
        ReloadGameimg.dispose();
        OnHomeSongImg.dispose();
        OffHomeSongImg.dispose();
        backgroundbutton.dispose();

        // Dispose of the sprites (optional, as they usually depend on textures)
        sprite.getTexture().dispose();
        Levelsprite.getTexture().dispose();
        Exitsprite.getTexture().dispose();
        ReloadButton.getTexture().dispose();
        OnHomeSongSprite.getTexture().dispose();
        OffHomeSongSprite.getTexture().dispose();
        spritebutton.getTexture().dispose();

        // Dispose of the background music if it's not needed anymore
        GamePlayScreen.backgroundMusic.dispose();
    }

}
