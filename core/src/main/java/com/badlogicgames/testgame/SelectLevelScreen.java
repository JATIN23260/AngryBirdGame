package com.badlogicgames.testgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogicgames.testgame.LevelGame.FirstLevel;

public class SelectLevelScreen implements Screen {

    private Main game;
    private OrthographicCamera gamecame;
    private Viewport gameport;
    private Texture backImage;
    private Sprite backgSprite;
    private Texture LeftArrow;
    private Image leftimg;
    private Sprite leftsprite;

    private Texture RightArrow;
    private Sprite rightSprite;
    private ImageButton GoRight;


    private Texture First_level;
    private Sprite firstLevelSprite;
    private ImageButton first;


    private Texture Second_level;
    private Sprite secondLevel;
    private ImageButton second;


    private Texture ThirdLevel;
    private Sprite third_level;
    private ImageButton third;

    private Texture ExitButtonimg;
    private ImageButton ExitButton;
    private Sprite Exitsprite;

    private Stage stage;

    public static Music LevelMusic;


    public SelectLevelScreen(Main game)
    {
        this.game = game;

        gamecame = new OrthographicCamera();
        gameport = new ScreenViewport(gamecame);
        backImage = new Texture("backlevelimg.png");
        backgSprite = new Sprite(backImage);
        backgSprite.setPosition(0,0);

        LevelMusic = Gdx.audio.newMusic(Gdx.files.internal("LevelMusic.mp3"));
        LevelMusic.setLooping(true);
        LevelMusic.setVolume(1.5f);
        stage = new Stage(gameport);
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        LeftArrow = new Texture("LeftArrow.png");
        leftsprite = new Sprite(LeftArrow);
        leftimg = new Image(LeftArrow);

        RightArrow = new Texture("RightArrow.png");
        rightSprite = new Sprite(RightArrow);


        First_level = new Texture("Level1.png");
        firstLevelSprite = new Sprite(First_level);
        TextureRegionDrawable firstDrawable = new TextureRegionDrawable(firstLevelSprite);
        first = new ImageButton(firstDrawable);

        ExitButtonimg = new Texture("HomeButton.png");
        Exitsprite = new Sprite(ExitButtonimg);

        first.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                game.setScreen(new FirstLevel(game));
                GamePlayScreen.backgroundMusic.pause();
                LevelMusic.play();
            }
        });

        TextureRegionDrawable rightDrawable = new TextureRegionDrawable(rightSprite);
        GoRight = new ImageButton(rightDrawable);

        GoRight.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x , float y)
            {
                game.setScreen(new SelectLevelScreen2(game));
            }
        });


        Second_level = new Texture("Level2.png");
        secondLevel = new Sprite(Second_level);

        TextureRegionDrawable secondDrawable = new TextureRegionDrawable(secondLevel);
        second = new ImageButton(secondDrawable);


//        second.addListener(new ClickListener()
//        {
//
//        });

        ThirdLevel = new Texture("Level3.png");
        third_level = new Sprite(ThirdLevel);

        TextureRegionDrawable thirdDrawable = new TextureRegionDrawable(third_level);
        third = new ImageButton(thirdDrawable);

        TextureRegionDrawable ExitDrawable = new TextureRegionDrawable(Exitsprite);
        ExitButton = new ImageButton(ExitDrawable);
        ExitButton.setPosition(8,35);
        ExitButton.setSize(150,150);

        ExitButton.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                game.setScreen(new HomePage(game));
            }
        });

        stage.addActor(ExitButton);

        table.add(leftimg).padRight(0);
        table.add(first).size(600,400).padRight(0);
        table.add(second).size(500,300).padRight(0);
        table.add(third).size(500,300).padRight(0);
        table.add(GoRight).size(150,150);
        table.center();

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
        game.batch.draw(backgSprite,0,0,gameport.getWorldWidth(),gameport.getWorldHeight());
//        game.batch.draw(leftsprite,50, Gdx.graphics.getHeight()/2f - leftsprite.getHeight()/2f);
        game.batch.end();
        stage.act(delta); // Update the stage with time delta
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width,height,true);
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
        // Dispose of all textures
        backImage.dispose();
        LeftArrow.dispose();
        RightArrow.dispose();
        First_level.dispose();
        Second_level.dispose();
        ThirdLevel.dispose();
        ExitButtonimg.dispose();

        // Dispose of music
        LevelMusic.dispose();

        // Dispose of the stage
        stage.dispose();
    }

}
