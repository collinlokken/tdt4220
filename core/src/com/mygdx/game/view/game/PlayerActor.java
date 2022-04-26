package com.mygdx.game.view.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.model.game.component.PositionComponent;
import com.mygdx.game.model.game.entity.Player;
import com.mygdx.game.view.game.spriteActors.GameActor;

public class PlayerActor extends GameActor<Player> {
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

    public PlayerActor(int numberOfAnimations, ArrayList<PlayerItem> playerItems, ArrayList<Texture> textures, Player player){
        super(numberOfAnimations, textures, player);
        this.playerItems = playerItems;
    }



    @Override
    public void act(float delta) {
        if(this.getY() > 0)
        {
            this.playerAnimations.get(1).update(delta);
            this.setSprite(this.playerAnimations.get(1).getSpriteFrame());
        }
        else
        {
            this.playerAnimations.get(0).update(delta);
            this.setSprite(this.playerAnimations.get(0).getSpriteFrame());
        }
        for (PlayerItem playerItem : this.playerItems){
            playerItem.getAnimations().get(0).update(delta);
        }
        this.setWidth(0);
        this.setHeight(0);
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
