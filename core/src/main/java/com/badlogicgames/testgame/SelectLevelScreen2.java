package com.badlogicgames.testgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import com.badlogicgames.testgame.LevelGame.FourthLevel;

public class SelectLevelScreen2 implements Screen {

    private Main game;
    private OrthographicCamera gamecame;
    private Viewport gameport;
    private Texture backImage;
    private Sprite backgSprite;
    private Texture LeftArrow;
    private Sprite leftsprite;
    private ImageButton left;

    private Texture RightArrow;
    private Sprite rightSprite;
    private ImageButton GoRight;


    private Texture Fourth_level;
    private Sprite fourthLevelSprite;
    private ImageButton Fourth;


    private Texture Fifth_level;
    private Sprite FifthLevel;
    private ImageButton Fifth;


    private Texture ExitButtonimg;
    private ImageButton ExitButton;
    private Sprite Exitsprite;



    private Stage stage;
    public SelectLevelScreen2(Main game)
    {
        this.game = game;
        gamecame = new OrthographicCamera();
        gameport = new ScreenViewport(gamecame);
        backImage = new Texture("backlevelimg.png");
        backgSprite = new Sprite(backImage);
        backgSprite.setPosition(0,0);

        stage = new Stage(gameport);
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        LeftArrow = new Texture("LeftArrow.png");
        leftsprite = new Sprite(LeftArrow);

        RightArrow = new Texture("RightArrow.png");
        rightSprite = new Sprite(RightArrow);


        Fourth_level = new Texture("Level4.png");
        fourthLevelSprite = new Sprite(Fourth_level);

        TextureRegionDrawable firstDrawable = new TextureRegionDrawable(fourthLevelSprite);
        Fourth = new ImageButton(firstDrawable);
        ExitButtonimg = new Texture("HomeButton.png");
        Exitsprite = new Sprite(ExitButtonimg);

//        Fourth.addListener(new ClickListener()
//        {
//            @Override
//            public void clicked(InputEvent event, float x, float y)
//            {
//                game.setScreen(new FourthLevel(game));
//            }
//        });

        TextureRegionDrawable leftDrawable = new TextureRegionDrawable(leftsprite);
        left = new ImageButton(leftDrawable);

        left.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x , float y)
            {
                game.setScreen(new SelectLevelScreen(game));
            }
        });


        Fifth_level = new Texture("Level5.png");
        FifthLevel = new Sprite(Fifth_level);

        TextureRegionDrawable secondDrawable = new TextureRegionDrawable(FifthLevel);
        Fifth = new ImageButton(secondDrawable);


//        Fifth.addListener(new ClickListener()
//        {
//
//        });

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

        table.add(left).padRight(0);
        table.add(Fourth).size(500,400).padRight(0);
        table.add(Fifth).size(500,350).padRight(0);
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
        // Dispose of all textures to free up memory
        backImage.dispose();
        LeftArrow.dispose();
        RightArrow.dispose();
        Fourth_level.dispose();
        Fifth_level.dispose();
        ExitButtonimg.dispose();

        // Dispose of the stage
        stage.dispose();
    }

}
