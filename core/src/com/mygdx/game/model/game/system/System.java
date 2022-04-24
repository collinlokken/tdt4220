package com.mygdx.game.model.game.system;

import com.mygdx.game.model.game.component.Component;
import com.mygdx.game.model.game.entity.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public abstract class System
{
    private HashMap<Class<? extends Component>, HashMap<Entity, Component>> componentGroups;



    public System(Class<? extends Component>...components)
    {
        this.componentGroups = new HashMap<>();
        for(Class<? extends Component> comp : components)
        {
            this.componentGroups.put(comp, new HashMap<Entity, Component>());
        }
    }

    public void addComponent(Component component)
    {
        String componentClassName = component.getClass().getName();
        for(Class<? extends  Component> type : this.componentGroups.keySet())
        {
            if(type.isInstance(component))
            {
                this.componentGroups.get(type).put(component.getEntity(), component);
                return;
            }
        }
        throw new IllegalArgumentException(this.getClass().getName() + " does not handle " + componentClassName);
    }

    public  <T extends Component> Collection<T> getComponents(Class<T> type)
    {
        if(!this.componentGroups.containsKey(type))
            throw new IllegalArgumentException("Unknown component type " + type.getName());
        return (Collection<T>) this.componentGroups.get(type).values();
    }

    public <T extends Component> T getComponent(Class<T> type, int entity)
    {
        String className = type.getName();
        if(!this.componentGroups.containsKey(className))
            throw new IllegalArgumentException("Unknown type provided");
        HashMap<Entity, Component> group = this.componentGroups.get(className);
        if(!group.containsKey(entity))
            throw new IllegalArgumentException("Entity does not have a component of type " + className);
        return (T)group.get(entity);
    }

    public Collection<Class<? extends Component>>  getComponentTypes()
    {
        return  this.componentGroups.keySet();
    }

    public  Collection<Entity> getEntities(Class<? extends Component> type)
    {
        if(!this.componentGroups.containsKey(type))
            throw new IllegalArgumentException("The system does not handle components of type " + type.getName());
        return this.componentGroups.get(type).keySet();
    }

    public <T extends Component> Collection<T> getComponentsOfType(Class<T> type)
    {
        if(!this.componentGroups.containsKey(type))
            throw new IllegalArgumentException("System does not handle components of type " + type.getName());
        return (Collection<T>)this.componentGroups.get(type).values();
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
