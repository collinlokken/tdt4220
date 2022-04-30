package com.mygdx.game.model.game.component;

import com.mygdx.game.model.game.entity.Entity;

public abstract class Component
{
    private Entity entity;


    public void setEntity(Entity entity)
    {
        this.entity = entity;
    }

    public  Entity getEntity()
    {
        return this.entity;
    }

    public  String getType()
    {
        return this.getClass().getName();
    }
}
