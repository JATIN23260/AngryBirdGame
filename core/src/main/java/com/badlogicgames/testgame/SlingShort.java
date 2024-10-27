package com.badlogicgames.testgame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SlingShort {
    public Texture SlingShortImg;
    public Sprite SlingShortSprite;
    public SlingShort()
    {
        this.SlingShortImg = new Texture("SlingShort3.png");
        this.SlingShortSprite = new Sprite(SlingShortImg);
    }

    public Texture getSprite() {
        return SlingShortImg;
    }
}
