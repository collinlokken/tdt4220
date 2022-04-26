package com.mygdx.game.view.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Arrays;

import com.mygdx.game.model.game.entity.Player;
import com.mygdx.game.view.game.EntityActor;

public class PlayerActor extends EntityActor<Player> {
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

    public PlayerActor(Player player){
        super(2, new ArrayList<Texture>(Arrays.asList(
                new Texture(Gdx.files.internal("player1.png")),
                new Texture(Gdx.files.internal("player2.png")),
                new Texture(Gdx.files.internal("player3.png")),
                new Texture(Gdx.files.internal("player4.png")),
                new Texture(Gdx.files.internal("player_flying.png")),
                new Texture(Gdx.files.internal("player_flying.png")),
                new Texture(Gdx.files.internal("player_flying.png")),
                new Texture(Gdx.files.internal("player_flying.png"))
                )), player);


        Texture shieldTexture = new Texture(Gdx.files.internal("shield.png"));

        PlayerItem shield = new PlayerItem(player,1, new ArrayList<>(Arrays.asList(shieldTexture)));
        Texture flame1 = new Texture(Gdx.files.internal("flame1.png"));
        Texture flame2 = new Texture(Gdx.files.internal("flame2.png"));
        Texture flame3 = new Texture(Gdx.files.internal("flame3.png"));
        PlayerItem flames = new PlayerItem(player, 1, new ArrayList<>(Arrays.asList(flame1, flame2, flame3)));
        Texture star1 = new Texture(Gdx.files.internal("star1.png"));
        Texture star2 = new Texture(Gdx.files.internal("star2.png"));
        Texture star3 = new Texture(Gdx.files.internal("star3.png"));
        Texture star4 = new Texture(Gdx.files.internal("star4.png"));
        PlayerItem  stars = new PlayerItem(player, 1, new ArrayList<>(Arrays.asList(star1, star2, star3, star4)));
        this.playerItems = new ArrayList<PlayerItem>(Arrays.asList(shield, flames, stars));

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
        this.sprite.setSize(this.getWidth(), this.getHeight());
        this.sprite.setPosition(this.getX(), this.getY());
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
