package com.badlogicgames.testgame.PigsPackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MediumPig {
    private Texture MediumPigImg;
    public Sprite MediumPigSprite;
    public MediumPig()
    {
        this.MediumPigImg = new Texture("MediumPig.png");
        this.MediumPigSprite = new Sprite(MediumPigImg);
    }

    public Texture getSprite() {
        return MediumPigImg;
    }
}
