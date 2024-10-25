package com.badlogicgames.testgame.PigsPackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SmallPig {
    private Texture SmallPigImg;
    public Sprite SmallPigSprite;
    public SmallPig()
    {
        this.SmallPigImg = new Texture("SmallPig.png");
        this.SmallPigSprite = new Sprite(SmallPigImg);
    }

    public Texture getSprite() {
        return SmallPigImg;
    }
}
