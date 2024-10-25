package com.badlogicgames.testgame.LevelGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
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
import com.badlogicgames.testgame.*;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogicgames.testgame.BirdsPackage.RedBird;
import com.badlogicgames.testgame.MaterialPackage.WoodsBlocks;
import com.badlogicgames.testgame.PigsPackage.MediumPig;
import com.badlogicgames.testgame.PigsPackage.SmallPig;

public class FirstLevel implements Screen {
    private Main game;
    private Texture backgroundtexture;
    private OrthographicCamera gamecame;
    private Viewport gameport;
    private Texture pauseButton;
    private Sprite PauseSprite;
    private ImageButton pause;
    private Stage stage;
    private static boolean OnScreen = false;

    // Number of birds
    private RedBird redBird1;
    private RedBird redBird2;
    private RedBird redBird3;

    // Number of Pigs
    private SmallPig smallPig;
    private SmallPig smallPig1;
    private SmallPig smallPig2;
    private MediumPig mediumPig;

    // Number of Blocks
    private WoodsBlocks woodsBlocks;
    private WoodsBlocks woodsBlocks1;
    private WoodsBlocks woodsBlocks2;
    private WoodsBlocks woodsBlocks3;
    private WoodsBlocks woodsBlocks4;
    private WoodsBlocks woodsBlocks5;
    private WoodsBlocks woodsBlocks6;
    private WoodsBlocks woodsBlocks7;
    private WoodsBlocks woodsBlocks8;
    private WoodsBlocks woodsBlocks9;
    private WoodsBlocks woodsBlocks10;
    private WoodsBlocks woodsBlocks11;

    // SlingShort
    private SlingShort slingShort;

    public FirstLevel(Main game) {
        this.game = game;

        // bird creation
        redBird1 = new RedBird();
        redBird2 = new RedBird();
        redBird3 = new RedBird();
        redBird1.BirdSprite.setSize(40,40);
        redBird2.BirdSprite.setSize(40,40);
        redBird3.BirdSprite.setSize(40,40);

        // Pigs Creation
        smallPig = new SmallPig();
        smallPig1 = new SmallPig();
        smallPig2 = new SmallPig();
        mediumPig = new MediumPig();

        smallPig.SmallPigSprite.setSize(40,40);
        smallPig1.SmallPigSprite.setSize(40,40);
        smallPig2.SmallPigSprite.setSize(40,40);
        mediumPig.MediumPigSprite.setSize(60,60);
        // Sling short creation
        slingShort = new SlingShort();
        slingShort.SlingShortSprite.setSize(55,138);

        // Blocks creation
        woodsBlocks = new WoodsBlocks();
        woodsBlocks1 = new WoodsBlocks();
        woodsBlocks2 = new WoodsBlocks();
        woodsBlocks3 = new WoodsBlocks();
        woodsBlocks4 = new WoodsBlocks();
        woodsBlocks5  = new WoodsBlocks();
        woodsBlocks6  = new WoodsBlocks();
        woodsBlocks7  = new WoodsBlocks();


        woodsBlocks.UpWoodenSprite.setSize(190,220);
        woodsBlocks1.UpWoodenSprite.setSize(190,220);

        woodsBlocks.DownWoodenSprite.setSize(190,220);
        woodsBlocks1.DownWoodenSprite.setSize(380,220);

        woodsBlocks2.UpWoodenSprite.setSize(190,220);
        woodsBlocks3.UpWoodenSprite.setSize(190,220);

        woodsBlocks4.UpWoodenSprite.setSize(190,220);
        woodsBlocks5.UpWoodenSprite.setSize(190,220);
        woodsBlocks6.UpWoodenSprite.setSize(190,220);
        woodsBlocks7.UpWoodenSprite.setSize(190,220);

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

        // Create an InputMultiplexer to handle multiple input processors
        InputMultiplexer multiplexer = new InputMultiplexer();

        // Add the stage as a processor
        multiplexer.addProcessor(stage);

        // Add custom InputAdapter for key events
        multiplexer.addProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.W) {
                    SelectLevelScreen.LevelMusic.pause();
                    GamePlayScreen.backgroundMusic.play();
                    game.setScreen(new Win(game));
                    return true; // Return true to indicate that the event was handled
                }
                if (keycode == Input.Keys.L) {
                    SelectLevelScreen.LevelMusic.pause();
                    GamePlayScreen.backgroundMusic.play();
                    game.setScreen(new Loose(game));
                    return true; // Return true to indicate that the event was handled
                }
                return false; // Return false to propagate the event further if not handled
            }
        });

        // Set the multiplexer as the input processor
        Gdx.input.setInputProcessor(multiplexer);
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

        // Ensure the size is set before drawing

        game.batch.begin();
        game.batch.draw(backgroundtexture, 0, 0, gameport.getWorldWidth(), gameport.getWorldHeight());
        slingShort.SlingShortSprite.setPosition(550, 390);
        slingShort.SlingShortSprite.draw(game.batch);

        redBird1.BirdSprite.setPosition(555,490);
        redBird1.BirdSprite.draw(game.batch);

        redBird2.BirdSprite.setPosition(500,393);
        redBird2.BirdSprite.draw(game.batch);

        redBird3.BirdSprite.setPosition(400,323);
        redBird3.BirdSprite.draw(game.batch);

        // ground floor level first upward which is left to 2nd upward
        woodsBlocks.UpWoodenSprite.setPosition(gameport.getWorldWidth()/2 + 70,312);
        woodsBlocks.UpWoodenSprite.draw(game.batch);
        woodsBlocks1.UpWoodenSprite.setPosition(gameport.getWorldWidth()/2 + 95,312);
        woodsBlocks1.UpWoodenSprite.draw(game.batch);

        // ground floor 2nd Upward is right to first upward
        woodsBlocks2.UpWoodenSprite.setPosition(gameport.getWorldWidth()/2 + 300,312);
        woodsBlocks2.UpWoodenSprite.draw(game.batch);

        woodsBlocks3.UpWoodenSprite.setPosition(gameport.getWorldWidth()/2 + 325,312);
        woodsBlocks3.UpWoodenSprite.draw(game.batch);

        // first floor upward which is left to 2nd first floor upward
        woodsBlocks.UpWoodenSprite.setPosition(gameport.getWorldWidth()/2 + 145,512);
        woodsBlocks.UpWoodenSprite.draw(game.batch);
        woodsBlocks1.UpWoodenSprite.setPosition(gameport.getWorldWidth()/2 + 120,512);
        woodsBlocks1.UpWoodenSprite.draw(game.batch);

        // first floor upward which is left to 2nd first floor upward
        woodsBlocks.UpWoodenSprite.setPosition(gameport.getWorldWidth()/2 + 250,512);
        woodsBlocks.UpWoodenSprite.draw(game.batch);
        woodsBlocks1.UpWoodenSprite.setPosition(gameport.getWorldWidth()/2 + 275,512);
        woodsBlocks1.UpWoodenSprite.draw(game.batch);

        // Downward and lies on top of ground floor
        woodsBlocks.DownWoodenSprite.setPosition(gameport.getWorldWidth()/2 + 194,600);
        woodsBlocks.DownWoodenSprite.draw(game.batch);
        woodsBlocks1.DownWoodenSprite.setPosition(gameport.getWorldWidth()/2 + 100,400);
        woodsBlocks1.DownWoodenSprite.draw(game.batch);

        // Small pigs implementation
        smallPig.SmallPigSprite.setPosition(gameport.getWorldWidth()/2 + 265,722);
        smallPig.SmallPigSprite.draw(game.batch);
        smallPig1.SmallPigSprite.setPosition(gameport.getWorldWidth()/2 + 142,522);
        smallPig1.SmallPigSprite.draw(game.batch);
        smallPig2.SmallPigSprite.setPosition(gameport.getWorldWidth()/2 + 395,522);
        smallPig2.SmallPigSprite.draw(game.batch);


        // Medium pig implementation
        mediumPig.MediumPigSprite.setPosition(gameport.getWorldWidth()/2 + 260,522);
        mediumPig.MediumPigSprite.draw(game.batch);

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
        backgroundtexture.dispose(); // Dispose of the background texture
        pauseButton.dispose(); // Dispose of the pause button texture
        stage.dispose(); // Dispose of the stage
    }
}
