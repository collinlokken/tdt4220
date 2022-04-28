package com.mygdx.game.model.game.entity;

import com.mygdx.game.model.game.component.HitboxComponent;
import com.mygdx.game.model.game.component.PositionComponent;
import com.mygdx.game.model.game.component.StandShieldComponent;
import com.mygdx.game.model.game.component.StandShieldRewardComponent;
import com.mygdx.game.model.game.component.VelocityComponent;
import com.mygdx.game.model.game.entity.Entity;

public class CoffeeCup extends Entity
{
    public  static  final int height = 15;
    public  static  final int width = 10;

    public CoffeeCup(PositionComponent position, VelocityComponent velocity)
    {
        super(position, new HitboxComponent(CoffeeCup.width, CoffeeCup.height, position), new StandShieldRewardComponent(), velocity);
    }
}
