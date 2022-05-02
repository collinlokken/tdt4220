package com.mygdx.game.view.game;

import com.mygdx.game.model.game.entity.Entity;

import java.lang.reflect.InvocationTargetException;

public class EntityActorFactory
{
    private  static EntityActorFactory instance;
    public  static EntityActorFactory getInstance()
    {
        if(instance == null)
            instance = new EntityActorFactory();
        return instance;
    }

    private EntityActorFactory()
    {

    }
    public EntityActor createActor (Entity entity) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //Using Reflection to dynamically create new actor by using the convention that all actor classes have the name of format: <EntityName>Actor
        Class<? extends EntityActor> actorClass = (Class<? extends EntityActor>) Class.forName("com.mygdx.game.view.game.actors.".concat(entity.getClass().getSimpleName()).concat("Actor"));
        return actorClass.getConstructor(entity.getClass()).newInstance(entity);
    }
}
