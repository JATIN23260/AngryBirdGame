package com.badlogicgames.testgame.BirdsPackage;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class RedBird implements Birds {
    private Texture BirdImage;
    public Sprite BirdSprite;
    private Integer NumberOfHits;
    private Music MyMusic;
    public RedBird()
    {
        this.BirdImage = new Texture("redBird.png");
        this.BirdSprite = new Sprite(BirdImage);
    }

    public Texture getSprite() {
        return BirdImage;
    }
}
