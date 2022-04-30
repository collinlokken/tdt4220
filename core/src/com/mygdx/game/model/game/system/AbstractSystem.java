package com.mygdx.game.model.game.system;

import com.mygdx.game.model.game.Game;
import com.mygdx.game.model.game.component.Component;
import com.mygdx.game.model.game.component.ShieldComponent;
import com.mygdx.game.model.game.component.ShieldRewardComponent;
import com.mygdx.game.model.game.entity.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;


import sun.jvm.hotspot.oops.Array;

public abstract class AbstractSystem
{
    private HashMap<Class<? extends Component>, HashMap<Entity, Component>> componentGroups;


    public void reset()
    {
        for(Class<? extends Component> type : this.componentGroups.keySet())
        {
            this.componentGroups.get(type).clear();
        }
    }

    public AbstractSystem(Class<? extends Component>...components)
    {
        this.componentGroups = new HashMap<>();
        for(Class<? extends Component> comp : components)
        {
            this.componentGroups.put(comp, new HashMap<Entity, Component>());
        }
    }

    public void addComponent(Component component)
    {
        for(Class<? extends  Component> type : this.componentGroups.keySet())
        {
            if(type.isInstance(component))
            {
                if(type == ShieldRewardComponent.class && this.getClass() == ShieldSystem.class)
                    System.out.println("Added shield to Shield system");
                this.componentGroups.get(type).put(component.getEntity(), component);
                return;
            }
        }
    }

    public  boolean handlesComponent(Component component)
    {
        for(Class<? extends  Component> type : this.componentGroups.keySet())
        {
            if(type.isInstance(component))
                return true;
        }
        return false;
    }

    public  <T extends Component> Collection<T> getComponents(Class<T> type)
    {
        if(!this.componentGroups.containsKey(type))
            throw new IllegalArgumentException("Unknown component type " + type.getName());
        return new ArrayList(this.componentGroups.get(type).values());
    }

    public Collection<Class<? extends Component>>  getComponentTypes()
    {
        return  this.componentGroups.keySet();
    }

    public Collection<Entity> getEntities(Class<? extends Component> type)
    {
        if(!this.componentGroups.containsKey(type))
            throw new IllegalArgumentException("The system does not handle components of type " + type.getName());
        return new ArrayList<>(this.componentGroups.get(type).keySet());
    }

    public <T extends Component> Collection<T> getComponentsOfType(Class<T> type)
    {
        if(!this.componentGroups.containsKey(type))
            throw new IllegalArgumentException("System does not handle components of type " + type.getName());
        return new ArrayList(this.componentGroups.get(type).values());
    }

    public  void removeEntityComponents(Entity e)
    {
        for(Component component : e.getComponents())
        {
            this.removeComponent(component);
        }
    }

    public void removeComponent(Component component)
    {
        for(Class<? extends  Component> type : this.componentGroups.keySet())
        {
            if(type.isInstance(component))
                this.componentGroups.get(type).remove(component.getEntity());
        }
    }

    public  abstract void update(float dt);

}
