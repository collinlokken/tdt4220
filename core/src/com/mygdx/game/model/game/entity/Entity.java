package com.mygdx.game.model.game.entity;

import com.mygdx.game.model.game.Game;
import com.mygdx.game.model.game.component.Component;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public abstract class Entity
{
    private  int id = -1;
    private  Collection<Component> components;
    private Game game;


    public Entity(Component... components)
    {
        this.components = new ArrayList<>();
        for(Component component : components)
        {
            this.addComponent(component);
        }
    }

    public void addComponent(Component component)
    {
        this.components.add(component);
        if(component.getEntity() != null && component.getEntity() != this)
            throw new IllegalArgumentException("Component belongs to a different entity");
        component.setEntity(this);
    }

    public  <T extends Component> T getComponent(Class<T> type)
    {
        for(Component component : this.getComponents())
        {
            if (component.getClass().equals(type))
                return (T) component;
        }
        return null;
    }
    public Collection<Component> getComponents()
    {
        return this.components;
    }

    public <T extends Component> Collection<T> getComponentsOfType(Class<? extends  Component> type)
    {
        Collection<T> result = new ArrayList<T>();
        for(Component component : this.components)
        {
            if(type.isInstance(component))
                result.add((T)component);
        }
        return result;
    }

    public boolean hasComponentOfType(Class<? extends  Component> type)
    {
        for(Component c : this.components)
        {
            if(type.isInstance(c))
                return true;
        }
        return false;
    }

    public void removeComponent(Component component)

    {
        this.components.remove(component);
        component.setEntity(null);
    }

    public  void setId(int id)
    {
        this.id = id;
    }

    public  int getId()
    {
        return this.id;
    }

    public  void setGame(Game game)
    {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
