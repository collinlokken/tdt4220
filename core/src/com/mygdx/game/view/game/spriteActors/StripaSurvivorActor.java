package com.mygdx.game.view.game.spriteActors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.view.game.PlayerActor;
import com.mygdx.game.view.game.PlayerAnimation;
import java.util.Arrays;

public abstract class StripaSurvivorActor extends Actor {

    private Sprite sprite;
    private Vector2 position;
    private boolean isPlayer;
    private PlayerAnimation playerAnimation;
    protected Array<PlayerAnimation> playerAnimations;
    protected ANIMATION_TYPES activeAnimation;

    public enum ANIMATION_TYPES{
        RUNNING,
        DOWN,
        UP
    }

    public StripaSurvivorActor(int x, int y, int width, int height, int numberOfAnimations, Texture... textures){
        this.playerAnimations = new Array<PlayerAnimation>();
        for (int i=0; i < numberOfAnimations; i++){
            this.playerAnimation = playerAnimation.getInstance(textures.length/numberOfAnimations, 0.1f, Arrays.copyOfRange(textures, i*textures.length/numberOfAnimations, i*textures.length/numberOfAnimations+textures.length/numberOfAnimations));
            this.playerAnimation.getSpriteFrame().setPosition((float)x, (float)y);
            this.playerAnimation.getSpriteFrame().setSize((float)width, (float)height);
            this.position = new Vector2(this.playerAnimation.getSpriteFrame().getX(), this.playerAnimation.getSpriteFrame().getY());
            this.playerAnimations.add(this.playerAnimation);
        }

    }

    public void setActorPosition(float x, float y) {

        for (PlayerAnimation playerAnimation : this.playerAnimations){
            playerAnimation.setSpritePosition(x, y);
            playerAnimation.setSpriteSize((int)playerAnimation.getSpriteFrame().getWidth(), (int)playerAnimation.getSpriteFrame().getWidth());
        }

    }

    public Sprite getSprite() {
        return this.playerAnimation.getSpriteFrame();
    }


    public void draw(Batch batch, float parentAlpha) {
        if (activeAnimation == ANIMATION_TYPES.UP){
            this.playerAnimations.get(1).getSpriteFrame().draw(batch);
        }
        else if (activeAnimation == ANIMATION_TYPES.DOWN){
            this.playerAnimations.get(2).getSpriteFrame().draw(batch);
        }
        else{
            this.playerAnimations.get(0).getSpriteFrame().draw(batch);
        }
    }


    public void act(float delta) {
        if (activeAnimation == ANIMATION_TYPES.UP){
            this.playerAnimations.get(1).update(delta);
        }
        else if (activeAnimation == ANIMATION_TYPES.DOWN){
            this.playerAnimations.get(2).update(delta);
        }
        else{
            this.playerAnimations.get(0).update(delta);
        }
        this.setWidth(0);
        this.setHeight(0);
    }

    public void setActiveAnimation(ANIMATION_TYPES animationType){
        this.activeAnimation = animationType;
    }
}
