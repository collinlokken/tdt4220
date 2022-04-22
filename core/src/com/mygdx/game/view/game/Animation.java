package com.mygdx.game.view.game;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;
    private Array<Sprite> sprites;


    public Animation(int frameCount, float cycleTime, ArrayList<Texture> textures){
        this.sprites = new Array<Sprite>();
        for (Texture texture : textures){
            this.sprites.add(new Sprite(texture));
        }
        this.frameCount = frameCount;
        this.maxFrameTime = cycleTime / frameCount;
        this.frame = 0;
    }

    public void update(float dt){
        currentFrameTime += dt;
        if(currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }
        if(frame>= frameCount)
            frame = 0;
    }

    public Sprite getSpriteFrame(){
        return sprites.get(frame);
    }

    public void setSpriteSize(int width, int height){
        for (Sprite sprite : sprites){
            sprite.setSize(width, height);
        }
    }

    public int getFrame(){
        return this.frame;
    }

    public void setSpritePosition(float x, float y){
        for (Sprite sprite : sprites){
            sprite.setPosition(x, y);
        }
    }

}
