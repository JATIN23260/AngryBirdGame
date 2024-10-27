package com.badlogicgames.testgame.PigsPackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SmallPig extends Pigs {
    public SmallPig()
    {
        this.img= new Texture("SmallPig.png");
        this.imgSprite = new Sprite(img);
    }

    public Texture getSprite() {
        return this.img;
    }
}
