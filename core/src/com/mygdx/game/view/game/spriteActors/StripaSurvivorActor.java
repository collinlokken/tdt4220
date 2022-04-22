package com.mygdx.game.view.game.spriteActors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.mygdx.game.view.game.Animation;
import com.mygdx.game.view.game.PlayerItem;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class StripaSurvivorActor extends Actor {

    private Sprite sprite;
    private Vector2 position;
    private boolean isPlayer;
    private Animation animation;
    protected ArrayList<Animation> playerAnimations;
    protected ANIMATION_TYPES activeAnimation;
    private ArrayList<PlayerItem> playerItems;

    public enum ANIMATION_TYPES{
        RUNNING,
        DOWN,
        UP
    }

    public StripaSurvivorActor(int x, int y, int width, int height, int numberOfAnimations, ArrayList<PlayerItem> playerItems, ArrayList<Texture> textures){
        this.playerAnimations = new ArrayList<Animation>();
        this.playerItems = playerItems;
        for (int i=0; i < numberOfAnimations; i++){
            this.animation = new Animation(textures.size()/numberOfAnimations, 0.1f, new ArrayList<Texture>(textures.subList(i*textures.size()/numberOfAnimations, i*textures.size()/numberOfAnimations+textures.size()/numberOfAnimations)));
            this.animation.getSpriteFrame().setPosition((float)x, (float)y);
            this.animation.getSpriteFrame().setSize((float)width, (float)height);
            this.position = new Vector2(this.animation.getSpriteFrame().getX(), this.animation.getSpriteFrame().getY());
            this.playerAnimations.add(this.animation);
        }

    }

    public ArrayList<Animation> getAnimations(){
        return this.playerAnimations;
    }

    public void setActorPosition(float x, float y) {
        for (PlayerItem playerItem : this.playerItems){
            playerItem.setActorPosition(x, y);
        }

        for (Animation animation : this.playerAnimations){
            animation.setSpritePosition(x, y);
            animation.setSpriteSize((int) animation.getSpriteFrame().getWidth(), (int) animation.getSpriteFrame().getWidth());
        }

    }

    public Sprite getSprite() {
        return this.animation.getSpriteFrame();
    }


    public void draw(Batch batch, float parentAlpha) {
        if (activeAnimation == ANIMATION_TYPES.UP){
            this.playerAnimations.get(1).getSpriteFrame().draw(batch);
        }
        else if (activeAnimation == ANIMATION_TYPES.DOWN){
            this.playerAnimations.get(1).getSpriteFrame().draw(batch);
        }
        else{
            this.playerAnimations.get(0).getSpriteFrame().draw(batch);
        }
        for (PlayerItem playerItem : this.playerItems){
            playerItem.getAnimations().get(0).getSpriteFrame().draw(batch);
        }
    }


    public void act(float delta) {
        if (activeAnimation == ANIMATION_TYPES.UP){
            this.playerAnimations.get(1).update(delta);
        }
        else if (activeAnimation == ANIMATION_TYPES.DOWN){
            this.playerAnimations.get(1).update(delta);
        }
        else{
            this.playerAnimations.get(0).update(delta);
        }
        for (PlayerItem playerItem : this.playerItems){
            playerItem.getAnimations().get(0).update(delta);
        }
        this.setWidth(0);
        this.setHeight(0);
    }

    public void setActiveAnimation(ANIMATION_TYPES animationType){
        this.activeAnimation = animationType;
    }
}
