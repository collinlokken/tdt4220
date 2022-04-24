package com.mygdx.game.model.game.component;

public class ScoreRewardComponent extends Component
{
    private  int value;

    public  ScoreRewardComponent(int value)
    {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
