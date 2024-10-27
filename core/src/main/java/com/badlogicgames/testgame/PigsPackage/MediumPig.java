package com.badlogicgames.testgame.PigsPackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MediumPig extends Pigs{

    public MediumPig()
    {
        this.img = new Texture("MediumPig.png");
        this.imgSprite = new Sprite(img);
    }

    public Texture getSprite() {
        return this.img;
    }
}
