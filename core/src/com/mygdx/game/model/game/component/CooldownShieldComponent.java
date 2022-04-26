package com.mygdx.game.model.game.component;

import com.mygdx.game.model.game.entity.Entity;

public class CooldownShieldComponent extends  ShieldComponent
{

    @Override
    public boolean protectsAgainst(Entity e) {
        return true;
    }
}
