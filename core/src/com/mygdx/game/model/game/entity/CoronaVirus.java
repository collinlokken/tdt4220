package com.mygdx.game.model.game.entity;

import com.mygdx.game.model.game.component.DamageComponent;
import com.mygdx.game.model.game.component.HitboxComponent;
import com.mygdx.game.model.game.component.LinearlyTimeDependentAccelerationComponent;
import com.mygdx.game.model.game.component.PositionComponent;
import com.mygdx.game.model.game.component.VelocityComponent;
import com.mygdx.game.model.game.entity.Entity;

public class CoronaVirus extends Entity {

    public  static  final int height = 15;
    public  static  final int width = 15;

    public CoronaVirus(PositionComponent position, VelocityComponent velocity)
    {
        super(new DamageComponent(1), new HitboxComponent(CoronaVirus.width, CoronaVirus.height, position), position, velocity);
    }
}
