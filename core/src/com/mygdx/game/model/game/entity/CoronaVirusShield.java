package com.mygdx.game.model.game.entity;


import com.mygdx.game.model.game.component.HitboxComponent;
import com.mygdx.game.model.game.component.PositionComponent;

public class CoronaVirusShield extends Entity {

    public CoronaVirusShield(PositionComponent position)
    {
        super(position, new HitboxComponent(30, 30, position));

    }
}
