package com.badlogicgames.testgame.MaterialPackage;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class WoodsBlocks implements Material{
    private Texture UpWoodenBlock;
    public Sprite UpWoodenSprite;
    private Texture DownWoodenBlock;
    public Sprite DownWoodenSprite;
    public WoodsBlocks()
    {
        this.UpWoodenBlock = new Texture("UpWoodenBlock.png");
        this.UpWoodenSprite = new Sprite(UpWoodenBlock);
        this.DownWoodenBlock = new Texture("DownWoodenBlock.png");
        this.DownWoodenSprite =  new Sprite(DownWoodenBlock);
    }

    public Texture getSprite() {
        return UpWoodenBlock;
    }
}
