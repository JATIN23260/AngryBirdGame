package com.badlogicgames.testgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.audio.Music;

public class GamePlayScreen implements Screen {
    private Main game;
    private OrthographicCamera gamecam;
    private Viewport gameport;
    private Texture img;
    private Sprite sprite;
    private Texture playButtonImg;
    private Stage stage;
    private ImageButton playButton;
    public static Music backgroundMusic;

    public GamePlayScreen(Main game) {
        this.game = game;
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("MainMusic.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(0.5f);
        img = new Texture("angryBirdstartingimage.png");
        sprite = new Sprite(img);
        sprite.setPosition(0, 0);

        // Setup camera and viewport
        gamecam = new OrthographicCamera();
        gameport = new ScreenViewport(gamecam);

        // Initialize the stage
        stage = new Stage(gameport);
        Gdx.input.setInputProcessor(stage);

        // Load the play button image
        playButtonImg = new Texture("playbutton.png");

        // Create an ImageButton using the playButtonImg
        TextureRegionDrawable playButtonDrawable = new TextureRegionDrawable(playButtonImg);
        playButton = new ImageButton(playButtonDrawable);
        playButton.setSize(200,200);
        // Set the position for the button (centered on screen)
        playButton.setPosition((gameport.getWorldWidth() / 2 - playButton.getWidth() / 2),
            30
             );

        // Add ClickListener to the button to navigate to the next screen
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Navigate to the new screen, e.g., a new GameScreen or MenuScreen
                game.setScreen(new HomePage(game));
                backgroundMusic.play();// NextScreen is another screen class
            }
        });

        // Add the button to the stage
        stage.addActor(playButton);
    }

    @Override
    public void show() {
        gameport.apply();
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        ScreenUtils.clear(Color.BLACK);

        // Update camera and viewport
        gameport.apply();
        gamecam.update();

        // Set projection matrix for rendering
        game.batch.setProjectionMatrix(gamecam.combined);

        // Begin rendering the background sprite
        game.batch.begin();
        game.batch.draw(sprite, 0, 0, gameport.getWorldWidth(), gameport.getWorldHeight());
        game.batch.end();

        // Update the stage and draw UI elements (like the button)
        stage.act(delta); // Update the stage with time delta
        stage.draw();     // Draw all the stage actors (including button)
    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width, height, true);
        gamecam.position.set(gameport.getWorldWidth() / 2, gameport.getWorldHeight() / 2, 0);
        gamecam.update();
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
        // Dispose of the stage
        stage.dispose();

        // Dispose of textures and sprites
        img.dispose();
        playButtonImg.dispose();

        // Dispose of the background music
        backgroundMusic.dispose();
    }

}
