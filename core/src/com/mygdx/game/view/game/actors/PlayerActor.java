package com.mygdx.game.view.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.model.game.component.Component;
import com.mygdx.game.model.game.component.CooldownDurationComponent;
import com.mygdx.game.model.game.component.PositionComponent;
import com.mygdx.game.model.game.component.ShieldComponent;
import com.mygdx.game.model.game.entity.Player;
import com.mygdx.game.view.game.EntityActor;

public class PlayerActor extends EntityActor<Player> {
    private boolean shield = false;
    private boolean flames = false;
    private boolean stars = false;
    private boolean flying = false;
    private boolean boosting = false;


    private Animation playerWalkAnimation;

    private Sprite playerFlying;
    private Sprite shieldSprite;

    private Animation boostAnimation;
    private Animation starsAnimation;


    @Override
    protected void initialize() {
        this.playerWalkAnimation = new Animation(4, 0.3f, new ArrayList<>(Arrays.asList(
                new Texture(Gdx.files.internal("player1.png")),
                new Texture(Gdx.files.internal("player2.png")),
                new Texture(Gdx.files.internal("player3.png")),
                new Texture(Gdx.files.internal("player4.png")))));
        this.playerWalkAnimation.setSpriteSize((int)this.getWidth(), (int)this.getHeight());

        this.renderAnimation(this.playerWalkAnimation);

        this.playerFlying =  new Sprite(new Texture(Gdx.files.internal("player_flying.png")));
        this.playerFlying.setSize(this.getWidth(), this.getHeight());

        this.shieldSprite = new Sprite(new Texture(Gdx.files.internal("shield.png")));
        this.shieldSprite.setSize(this.getWidth()*1.2f, this.getHeight()*1.2f);

        Texture flame1 = new Texture(Gdx.files.internal("flame1.png"));
        Texture flame2 = new Texture(Gdx.files.internal("flame2.png"));
        Texture flame3 = new Texture(Gdx.files.internal("flame3.png"));
        this.boostAnimation = new Animation(3, 0.2f, new ArrayList<Texture>(Arrays.asList(flame1, flame2, flame3)));
        this.boostAnimation.setSpriteSize((int)this.getWidth(), (int)this.getHeight());

        Texture star1 = new Texture(Gdx.files.internal("star1.png"));
        Texture star2 = new Texture(Gdx.files.internal("star2.png"));
        Texture star3 = new Texture(Gdx.files.internal("star3.png"));
        Texture star4 = new Texture(Gdx.files.internal("star4.png"));


        this.starsAnimation = new Animation(4, 0.5f, new ArrayList<>(Arrays.asList(star1, star2, star3, star4)));
        this.starsAnimation.setSpriteSize((int)Math.round(1.2*this.getWidth()), (int)Math.round(1.2*this.getWidth()/4));
    }

    public PlayerActor(Player player){
        super(player);
    }





    @Override
    protected void update(float dt) {

        if(this.getY() > 0)
        {
            this.playerFlying.setPosition(this.getX(), this.getY());
            if(!this.flying)
            {
                this.flying = true;
                this.renderSprite(playerFlying);
                this.removeAnimation(playerWalkAnimation);
            }
            if(!this.entity.isBoosting() && this.boosting)
            {
                this.boosting = false;
                this.removeAnimation(this.boostAnimation);
            }
            else if(this.entity.isBoosting())
            {
                this.boostAnimation.setSpritePosition(this.getX()-25, this.getY() - this.getHeight());
                this.boostAnimation.update(dt);
                if(!this.boosting)
                {
                    this.boosting = true;
                    this.renderAnimation(this.boostAnimation);
                }

            }

        }
        else
        {
            this.playerWalkAnimation.update(dt);
            this.playerWalkAnimation.setSpritePosition(this.getX(), this.getY());
            if(this.flying)
            {
                this.removeSprite(playerFlying);
                this.flying = false;
                this.renderAnimation(this.playerWalkAnimation);
            }
        }
        if(this.entity.hasComponentOfType(ShieldComponent.class))
        {
            this.shieldSprite.setPosition(this.getX() - (1/5f)*this.getWidth(), this.getY() - (1/5f)*this.getHeight());
            if(!this.shield)
            {
                this.shield = true;
                this.renderSprite(this.shieldSprite);
            }
        }
        else if(!this.entity.hasComponentOfType(ShieldComponent.class) && this.shield)
        {
            this.shield = false;
            this.removeSprite(this.shieldSprite);
        }
        if(this.entity.hasComponentOfType(CooldownDurationComponent.class))
        {
            this.starsAnimation.update(dt);
            this.starsAnimation.setSpritePosition(this.getX(), this.getY() + (7f/8f)*this.getHeight());
            if(!this.stars)
            {
                this.stars = true;
                this.renderAnimation(starsAnimation);
           }
        }
        else if(!this.entity.hasComponentOfType(CooldownDurationComponent.class) && this.stars)
        {
            this.stars = false;
            this.removeAnimation(this.starsAnimation);
        }


    }




}
