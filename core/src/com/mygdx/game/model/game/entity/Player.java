package com.mygdx.game.model.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.game.component.BoostComponent;
import com.mygdx.game.model.game.component.GravityComponent;
import com.mygdx.game.model.game.component.HealthComponent;
import com.mygdx.game.model.game.component.HitboxComponent;
import com.mygdx.game.model.game.component.PositionComponent;
import com.mygdx.game.model.game.component.ScoreComponent;
import com.mygdx.game.model.game.component.ShieldConsumerComponent;
import com.mygdx.game.model.game.component.VelocityComponent;
import com.mygdx.game.model.game.entity.Entity;
import com.mygdx.game.view.game.GameView;

import java.util.ArrayList;

public class Player extends Entity
{
    private  float boostAcceleration;
    private  boolean isBoosting = false;
    public Player(PositionComponent position, float boostAcceleration)
    {
        super(position, new HitboxComponent(50, 100, position), new VelocityComponent(0, 0),new HealthComponent(3), new ShieldConsumerComponent(), new GravityComponent(9.81f),new ScoreComponent(0));
        this.boostAcceleration = boostAcceleration;
    }

    public void startBoosting()
    {
        if(this.isBoosting)
            return;
        this.addComponent(new BoostComponent(this.boostAcceleration));
        this.isBoosting = true;
    }

    public  void stopBoosting()
    {
        if(!this.isBoosting)
            return;
        this.removeComponent(this.getComponent(BoostComponent.class));
        this.isBoosting = false;
    }
}
