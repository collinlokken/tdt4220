package com.mygdx.game.model.game.system;

import com.mygdx.game.model.game.Game;
import com.mygdx.game.model.game.component.HitboxComponent;
import com.mygdx.game.model.game.component.PositionComponent;
import com.mygdx.game.model.game.component.VelocityComponent;
import com.mygdx.game.model.game.entity.CoffeeCup;
import com.mygdx.game.model.game.entity.CoronaVirus;
import com.mygdx.game.model.game.entity.CoronaVirusShield;
import com.mygdx.game.model.game.entity.Entity;
import com.mygdx.game.model.game.entity.Stand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

public class SpawnSystem extends AbstractSystem
{
    protected float time;

    private  Game game;
    private Random random;

    private int lastSpawnTime = 0;


    public  SpawnSystem(Game game)
    {
        super(HitboxComponent.class);
        this.game = game;
        this.time = 0;
        this.random = new Random();
        this.initialize(CoronaVirus.class, Stand.class);
    }

    private Collection<Class<? extends  Entity>> spawnableEntities;

    private  void initialize(Class<? extends Entity>... entities)
    {
        this.spawnableEntities = new ArrayList<>(Arrays.asList(entities));
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
        if(currentSecond != this.lastSpawnTime && currentSecond - this.lastSpawnTime == 5) // Here we need to implement spawning
        {
            this.lastSpawnTime = currentSecond;
            this.game.addEntity(this.getRandomEntity());
        }
    }

    private Entity getRandomEntity()
    {
        Entity entity = null;
        int nItems = 4;
        int choice = this.random.nextInt(nItems) + 1;
        System.out.println(choice);
        switch(choice)
        {
            case 1:
                entity = new CoronaVirus(new PositionComponent(this.game.getWidth(), (this.game.getHeight() - CoronaVirus.height)*Math.random()), new VelocityComponent(-20, 0));
                break;
            case 2:
                entity = new Stand(new PositionComponent(this.game.getWidth(), 0), new VelocityComponent(-20, 0));
                break;
            case 3:
                entity = new CoronaVirusShield(new PositionComponent(this.game.getWidth(), (this.game.getHeight() - CoronaVirusShield.height)*Math.random()), new VelocityComponent(-20, 0));
                break;
            case 4:
                entity = new CoffeeCup(new PositionComponent(this.game.getWidth(), (this.game.getHeight() - CoffeeCup.height)*Math.random()), new VelocityComponent(-20, 0));
                break;
        }
        return entity;
    }
}
