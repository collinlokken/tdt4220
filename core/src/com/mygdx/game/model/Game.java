package com.mygdx.game.model;

import com.mygdx.game.model.game.component.Component;
import com.mygdx.game.model.game.component.PositionComponent;
import com.mygdx.game.model.game.entity.Entity;
import com.mygdx.game.model.game.entity.Player;
import com.mygdx.game.model.game.system.PhysicsSystem;
import com.mygdx.game.model.game.system.ScoreSystem;
import com.mygdx.game.model.game.system.SpawnSystem;
import com.mygdx.game.model.game.system.System;

import java.util.ArrayList;


public class Game
{

    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<System> systems = new ArrayList<>();
    private int nextEntityId = 0;
    private Player player;




    public  Game()
    {
        this.systems.add(new PhysicsSystem());
        this.systems.add(new SpawnSystem(this));
        this.systems.add(new ScoreSystem(this));
        this.player = new Player(new PositionComponent(0, 0), (float) (1.5*9.81));
        this.addEntity(this.player);
    }


    public  Player getPlayerEntity()
    {
        return this.player;
    }

    public void addEntity(Entity e)
    {
        if(e.getId() != -1)
            throw new IllegalStateException("Entity has already been previously added.");
        e.setId(this.nextEntityId);
        this.nextEntityId++;
        this.entities.add(e);
        for(System sys : this.systems)
        {
            for(Component component : e.getComponents())
            {
                if(sys.getComponentTypes().contains(component.getType()))
                    sys.addComponent(component);
            }
        }
    }

    public void removeEntity(Entity e)
    {
        this.entities.remove(e);
        for(System sys : this.systems)
        {
            sys.removeEntityComponents(e);
        }
    }

    public void removeComponent(Component component)
    {
        component.getEntity().removeComponent(component);
        for(System sys : this.systems)
        {
            if(sys.getComponentTypes().contains((component.getType())))
                sys.removeComponent(component);
        }
    }

    public  void addComponent(Entity e, Component component)
    {
        e.addComponent(component);
        for(System sys: this.systems)
        {
            if(sys.getComponentTypes().contains((component.getType())))
                sys.addComponent(component);
        }
    }

    public void update(float dt)
    {
        for(System sys : this.systems)
        {
            sys.update(dt);
        }
    }








}
