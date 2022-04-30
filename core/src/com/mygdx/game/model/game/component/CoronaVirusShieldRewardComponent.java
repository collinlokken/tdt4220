package com.mygdx.game.model.game.component;

public class CoronaVirusShieldRewardComponent extends ShieldRewardComponent
{
    @Override
    public ShieldComponent reward() {
        return new CoronaVirusShieldComponent();
    }
}
