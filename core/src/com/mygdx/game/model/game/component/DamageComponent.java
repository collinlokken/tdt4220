package com.mygdx.game.model.game.component;

public class DamageComponent extends Component
{
    private  int value;
    public  DamageComponent(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return this.value;
    }
}
