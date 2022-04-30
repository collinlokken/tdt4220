package com.mygdx.game.model.game.entity;


import com.mygdx.game.model.game.component.CoronaVirusShieldComponent;
import com.mygdx.game.model.game.component.CoronaVirusShieldRewardComponent;
import com.mygdx.game.model.game.component.HitboxComponent;
import com.mygdx.game.model.game.component.PositionComponent;
import com.mygdx.game.model.game.component.VelocityComponent;

public class CoronaVirusShield extends Entity {

    public  static  final int height = 10;
    public  static  final int width = 10;

    public CoronaVirusShield(PositionComponent position, VelocityComponent velocity)
    {
        super(new HitboxComponent(CoronaVirusShield.width, CoronaVirusShield.height, position), new CoronaVirusShieldRewardComponent(), position, velocity);

    }
}
