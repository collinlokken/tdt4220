package com.mygdx.game.model.game.component;


import com.mygdx.game.model.game.entity.CoronaVirus;
import com.mygdx.game.model.game.entity.Entity;

public class CoronaVirusShieldComponent extends  ShieldComponent
{

    @Override
    public boolean protectsAgainst(Entity e) {
        return  e instanceof CoronaVirus;
    }
}
