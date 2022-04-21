package com.mygdx.game.view.game;

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

    private PlayerAnimation(TextureRegion region, int frameCount, float cycleTime){
        frames = new Array<TextureRegion>();
        sprites = new Array<Sprite>();
        int frameWidth = region.getRegionWidth() / frameCount;
        for(int i = 0; i < frameCount; i++){
            frames.add(new TextureRegion(region, i*frameWidth, 0, frameWidth, region.getRegionHeight()));
            sprites.add(new Sprite(frames.get(i)));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public static final PlayerAnimation getInstance(TextureRegion region, int frameCount, float cycleTime){
        if (instance == null){
            instance = new PlayerAnimation(region, frameCount, cycleTime);
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

    public void setSpriteSize(int width, int height){
        for (Sprite sprite : sprites){
            sprite.setSize(width, height);
        }
    }

    public Sprite getSpriteFrame(){
        return sprites.get(frame);
    }

}
