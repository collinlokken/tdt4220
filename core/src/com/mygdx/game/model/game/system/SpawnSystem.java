package com.mygdx.game.model.game.system;

import com.mygdx.game.model.game.Game;
import com.mygdx.game.model.game.component.HitboxComponent;
import com.mygdx.game.model.game.component.PositionComponent;
import com.mygdx.game.model.game.component.VelocityComponent;
import com.mygdx.game.model.game.entity.CoffeeCup;
import com.mygdx.game.model.game.entity.CoinItem;
import com.mygdx.game.model.game.entity.CoronaVirus;
import com.mygdx.game.model.game.entity.CoronaVirusShield;
import com.mygdx.game.model.game.entity.Entity;
import com.mygdx.game.model.game.entity.LifePointItem;
import com.mygdx.game.model.game.entity.Stand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

public class SpawnSystem extends AbstractSystem
{
    protected float time;

    private  Game game;

    private int lastSpawnTime = 0;

    public  SpawnSystem(Game game)
    {
        super(HitboxComponent.class);
        this.game = game;
        this.time = 0;
        this.initialize(CoronaVirus.class, Stand.class);
    }

    private Collection<Class<? extends  Entity>> spawnableEntities;

    private  void initialize(Class<? extends Entity>... entities)
    {
        this.spawnableEntities = new ArrayList<>(Arrays.asList(entities));
    }

    private double getSpawnPeriod()
    {
        return Math.min(0.05, 2*Math.pow(2, -this.time));
    }


    @Override
    public void update(float dt)
    {
        this.time += dt;
        for(HitboxComponent component : this.getComponents(HitboxComponent.class))
        {
            if(component.getPosition().getX() < -component.getWidth())
            {
                game.removeEntity(component.getEntity());
            }

        }
        int currentSecond = Math.round(this.time);
        if(currentSecond != this.lastSpawnTime && currentSecond - this.lastSpawnTime >= this.getSpawnPeriod()) // Here we need to implement spawning
        {
            this.lastSpawnTime = currentSecond;

            Entity e = this.getRandomEntity();
            if (e instanceof CoinItem)
            {
                this.game.addEntity(e);
                int yPos = (int)(Math.round(e.getComponent(PositionComponent.class).getY()));
                for (int i=1; i<5; i++){
                    Entity coin = this.getCoinItemEntity();
                    coin.getComponent(PositionComponent.class).setY(yPos);
                    coin.getComponent(PositionComponent.class).setX((int)(this.game.getWidth() + i*coin.getComponent(HitboxComponent.class).getWidth()));
                    this.game.addEntity(coin);
                }
            }
            else{
                this.game.addEntity(this.getRandomEntity());
            }
        }
    }

    private Entity getCoinItemEntity()
    {
        int xSpeed = (int)(-80f - this.time);
        return new CoinItem(new PositionComponent(this.game.getWidth(), (this.game.getHeight() - CoinItem.height)*Math.random()), new VelocityComponent(xSpeed, 0));
    }

    private Entity getRandomEntity()
    {
        double choice = Math.random();
        System.out.println(choice);
        int xSpeed = (int)(-80f - this.time);

        if (choice <= 0.25) {
            return new CoronaVirus(new PositionComponent(this.game.getWidth(), (this.game.getHeight() - CoronaVirus.height) * Math.random()), new VelocityComponent(xSpeed, 0));
        }
        else if (0.25 < choice && choice <= 0.5)
            return new Stand(new PositionComponent(this.game.getWidth(), 0), new VelocityComponent(xSpeed, 0));

        else if (0.5 < choice && choice <= 0.6)
                return new CoronaVirusShield(new PositionComponent(this.game.getWidth(), (this.game.getHeight() - CoronaVirusShield.height)*Math.random()), new VelocityComponent(xSpeed, 0));
        else if (0.6 < choice && choice <= 0.7)
            return new CoffeeCup(new PositionComponent(this.game.getWidth(), (this.game.getHeight() - CoffeeCup.height)*Math.random()), new VelocityComponent(xSpeed, 0));

        else if (0.7 < choice && choice <= 0.8)
            return new LifePointItem(new PositionComponent(this.game.getWidth(), (this.game.getHeight() - CoffeeCup.height)*Math.random()), new VelocityComponent(xSpeed, 0));

        else
            return new CoinItem(new PositionComponent(this.game.getWidth(), (this.game.getHeight() - CoinItem.height)*Math.random()), new VelocityComponent(xSpeed, 0));

    }
}
