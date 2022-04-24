package com.mygdx.game.model.game.entity;

import com.mygdx.game.model.game.component.DamageComponent;
import com.mygdx.game.model.game.component.HitboxComponent;
import com.mygdx.game.model.game.component.PositionComponent;
import com.mygdx.game.model.game.entity.Entity;

public class CoronaVirus extends Entity {
    public CoronaVirus(PositionComponent position)
    {
        super(new DamageComponent(1), new HitboxComponent(10, 10, position), position);
    }
}
