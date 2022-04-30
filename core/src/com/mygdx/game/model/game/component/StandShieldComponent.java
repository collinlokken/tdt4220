package com.mygdx.game.model.game.component;

import com.mygdx.game.model.game.entity.Stand;
import com.mygdx.game.model.game.entity.Entity;

public class StandShieldComponent extends  ShieldComponent
{
    @Override
    public boolean protectsAgainst(Entity e) {
        return e instanceof Stand;
    }
}
