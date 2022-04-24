package com.mygdx.game.view.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.view.game.spriteActors.StripaSurvivorActor;

public class PlayerActor extends StripaSurvivorActor {
    private static PlayerActor instance = null;
    private ArrayList<PlayerItem> playerItems;
    private ANIMATION_TYPES activeAnimation;
    private boolean shield = false;
    private boolean flames = false;
    private boolean blinking = false;

    public enum ANIMATION_TYPES{
        FLYING,
        RUNNING
    }

    private PlayerActor(int x, int y, int width, int height, int numberOfAnimations, ArrayList<PlayerItem> playerItems, ArrayList<Texture> textures){
        super(x, y, width, height, numberOfAnimations, textures);
        this.playerItems = playerItems;
    }

    @Override
    public void setActorPosition(float x, float y) {
        super.setActorPosition(x, y);
        for (PlayerItem playerItem : this.playerItems){
            playerItem.setActorPosition(x, y);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (activeAnimation == ANIMATION_TYPES.FLYING){
            this.playerAnimations.get(1).getSpriteFrame().draw(batch);
        }
        else{
            this.playerAnimations.get(0).getSpriteFrame().draw(batch);
        }
        for (PlayerItem playerItem : this.playerItems){
            if (playerItem.getTextures().contains(GameView.getInstance().shieldTexture)){
                if (this.shield){
                    playerItem.getAnimations().get(0).getSpriteFrame().draw(batch);
                }
            }
            else if (playerItem.getTextures().contains(GameView.getInstance().flame1)){
                if (this.flames){
                    playerItem.getAnimations().get(0).getSpriteFrame().draw(batch);
                }
            }
            else {
                if (this.blinking) {
                    playerItem.getAnimations().get(0).getSpriteFrame().draw(batch);
                }
            }

        }
    }

    @Override
    public void act(float delta) {
        if (activeAnimation == ANIMATION_TYPES.FLYING) {
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

    public static final PlayerActor getInstance(int x, int y, int width, int height, int numberOfAnimations, ArrayList<PlayerItem> playerItems, ArrayList<Texture> textures){
        if (instance == null){
            instance = new PlayerActor(x, y, width, height, numberOfAnimations, playerItems, textures);
        }
        return instance;
    }

    public void setActiveAnimation(ANIMATION_TYPES animationType){
        this.activeAnimation = animationType;
    }

    public void setShield(boolean shield){
        this.shield = shield;
    }

    public void setFlames(boolean flames){
        this.flames = flames;
    }

    public void blinking(boolean blinking){
        this.blinking = blinking;
    }





}
