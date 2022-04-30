package com.mygdx.game.model.game.component;

public class StandShieldRewardComponent extends  ShieldRewardComponent
{
    @Override
    public ShieldComponent reward() {
        return new StandShieldComponent();
    }
}
