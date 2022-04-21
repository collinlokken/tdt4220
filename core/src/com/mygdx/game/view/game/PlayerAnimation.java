package com.mygdx.game.view.game;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class PlayerAnimation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;
    private Array<Sprite> sprites;
    private static PlayerAnimation instance = null;

    public PlayerAnimation(int frameCount, float cycleTime, Texture... textures){
        sprites = new Array<Sprite>();
        for (Texture texture : textures){
            sprites.add(new Sprite(texture));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }


    public static final PlayerAnimation getInstance(int frameCount, float cycleTime, Texture... textures){
        if (instance == null){
            return new PlayerAnimation(frameCount, cycleTime, textures);
        }
        return instance;
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

    public void setSpritePosition(float x, float y){
        for (Sprite sprite : sprites){
            sprite.setPosition(x, y);
        }
    }

}
