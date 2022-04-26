package com.mygdx.game.model.game.system;

import com.mygdx.game.model.game.Game;
import com.mygdx.game.model.game.component.HitboxComponent;
import com.mygdx.game.model.game.component.PositionComponent;
import com.mygdx.game.model.game.entity.CoronaVirus;
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
            if(component.getPosition().getX() < component.getWidth())
                game.removeEntity(component.getEntity());
        }
        if(Math.round(this.time) % 5 == 0) // Here we need to implement spawning
        {

        }
    }

    private void getRandomItem()
    {
        int nItems = 6;
        int choice = this.random.nextInt(nItems);
        //PositionComponent component = new PositionComponent(random.nextInt() - 1, )

    }
}
