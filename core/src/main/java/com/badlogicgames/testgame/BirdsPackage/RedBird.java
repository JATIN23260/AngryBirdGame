package com.badlogicgames.testgame.BirdsPackage;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class RedBird extends Birds{
    public RedBird()
    {
        this.BirdImage = new Texture("redBird.png");
        this.BirdSprite = new Sprite(BirdImage);
    }

    public Texture getSprite() {
        return BirdImage;
    }
}
