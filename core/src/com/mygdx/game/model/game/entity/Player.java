package com.mygdx.game.model.game.entity;

import com.mygdx.game.model.game.component.BoostComponent;
import com.mygdx.game.model.game.component.GravityComponent;
import com.mygdx.game.model.game.component.HealthComponent;
import com.mygdx.game.model.game.component.HitboxComponent;
import com.mygdx.game.model.game.component.PositionComponent;
import com.mygdx.game.model.game.component.ScoreComponent;
import com.mygdx.game.model.game.component.ShieldConsumerComponent;
import com.mygdx.game.model.game.component.VelocityComponent;

public class Player extends Entity
{
    private  float boostAcceleration;
    private  boolean boosting = false;
    public Player(PositionComponent position, float boostAcceleration)
    {
        super(position, new HitboxComponent(15, 20, position), new VelocityComponent(0, 0),new HealthComponent(3), new ShieldConsumerComponent(), new GravityComponent(10*9.81f),new ScoreComponent(0));
        this.boostAcceleration = boostAcceleration;
    }

    public boolean isBoosting()
    {
        return this.boosting;
    }

    public void startBoosting()
    {
        if(this.boosting)
            return;
        this.addComponent(new BoostComponent(this.boostAcceleration));
        this.boosting = true;
    }

    public  void stopBoosting()
    {
        if(!this.boosting)
            return;
        this.removeComponent(this.getComponent(BoostComponent.class));
        this.boosting = false;
    }
}
