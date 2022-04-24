package com.mygdx.game.model.game.entity;

import com.mygdx.game.model.game.component.HitboxComponent;
import com.mygdx.game.model.game.component.PositionComponent;
import com.mygdx.game.model.game.entity.Entity;

public class CoffeeCup extends Entity
{

    public CoffeeCup(PositionComponent position)
    {
        super(position, new HitboxComponent(10, 10,position));
    }
}
