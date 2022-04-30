package com.mygdx.game.model.game;

import com.mygdx.game.model.game.component.Component;
import com.mygdx.game.model.game.component.PositionComponent;
import com.mygdx.game.model.game.component.ScoreComponent;
import com.mygdx.game.model.game.entity.Entity;
import com.mygdx.game.model.game.entity.Player;
import com.mygdx.game.model.game.system.HealthSystem;
import com.mygdx.game.model.game.system.PhysicsSystem;
import com.mygdx.game.model.game.system.ScoreSystem;
import com.mygdx.game.model.game.system.ShieldSystem;
import com.mygdx.game.model.game.system.SpawnSystem;
import com.mygdx.game.model.game.system.AbstractSystem;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;


public class Game
{

    private static Game instance;

    public static Game getInstance(float aspectRatio)
    {
        if(instance == null)
            instance = new Game(aspectRatio);
        return instance;
    }

    private final ArrayList<Entity> entities = new ArrayList<>();
    private final ArrayList<AbstractSystem> systems = new ArrayList<>();
    private final ArrayList<GameObserver> observers = new ArrayList<>();

    private int nextEntityId = 0;
    private Player player;

    private  float width;
    private  float height;

    private boolean started = false;

    public float getHeight() {
        return height;
    }

    public  float getWidth()
    {
        return this.width;
    }

    public  boolean isStarted()
    {
        return this.started;
    }
    private  Game(float aspectRatio)
    {
        this.height = 100;
        this.width = this.height*aspectRatio;
        this.systems.add(new PhysicsSystem(this));
        this.systems.add(new SpawnSystem(this));
        this.systems.add(new ScoreSystem(this));
        this.systems.add(new ShieldSystem(this));
        this.systems.add(new HealthSystem(this));
    }


    public  Player getPlayerEntity()
    {
        return this.player;
    }



    public void addObserver(GameObserver observer)
    {
        this.observers.add(observer);
    }

    public  void removeObserver(GameObserver observer)
    {
        this.observers.remove(observer);
    }
    public void endGame()
    {
        if(!this.started)
            throw new IllegalStateException("Cannot end a game that has not started.");

        System.out.println("GAME OVER");
        for(AbstractSystem sys : this.systems)
        {
            sys.reset();
        }
        this.started = false;
        this.entities.clear();
        ScoreComponent score = this.player.getComponent(ScoreComponent.class);

        for(GameObserver observer : this.observers)
        {
            observer.onGameEnded(this, player, score.getValue());
        }
        this.player = null;
    }
    public void startGame()
    {
        if(this.started)
            throw new IllegalStateException("Cannot start a game that is running.");
        this.started = true;
        this.player = new Player(new PositionComponent(width/4, 0, true), (float) (30*9.81));
        for(GameObserver observer : this.observers)
        {
            observer.onGameStarted(this);
        }
        this.addEntity(this.player);

    }
    public void addEntity(Entity e)
    {

        System.out.println("Adding entity " + e.getClass().getSimpleName());

        if(e.getId() != -1)
            throw new IllegalStateException("Entity has already been previously added.");
        e.setId(this.nextEntityId);
        e.setGame(this);
        this.nextEntityId++;
        this.entities.add(e);

        for(AbstractSystem sys : this.systems)
        {
            for(Component component : e.getComponents())
            {
                if(sys.handlesComponent(component))
                    sys.addComponent(component);

            }
        }
        for(GameObserver observer : this.observers)
        {
            observer.onEntityAdded(this, e);
        }
    }
    public void removeEntity(Entity e)
    {
        this.entities.remove(e);
        for(AbstractSystem sys : this.systems)
        {
            sys.removeEntityComponents(e);
        }
        for(GameObserver observer : this.observers)
        {
            observer.onEntityRemoved(this, e);
        }
    }

    public void removeComponent(Component component)
    {

        component.getEntity().removeComponent(component);
        for(AbstractSystem sys : this.systems)
        {
            if(sys.getComponentTypes().contains((component.getType())))
                sys.removeComponent(component);
        }
        for(GameObserver observer : this.observers)
        {
            observer.onEntityComponentRemoved(this, component.getEntity(), component);
        }
    }

    public  void addComponent(Entity e, Component component)
    {
        e.addComponent(component);
        for(AbstractSystem sys: this.systems)
        {
            if(sys.getComponentTypes().contains((component.getType())))
                sys.addComponent(component);
        }
        for(GameObserver observer : this.observers)
        {
            observer.onEntityComponentAdded(this, e, component);
        }
    }

    public void update(float dt)
    {
        if(!this.started)
            return;
        for(AbstractSystem sys : this.systems)
        {
            sys.update(dt);
        }
    }








}
