package com.mygdx.game.model.game.component;

import com.mygdx.game.model.game.entity.Entity;

public abstract  class ShieldComponent extends  Component
{
    public  abstract  boolean protectsAgainst(Entity e);

}
