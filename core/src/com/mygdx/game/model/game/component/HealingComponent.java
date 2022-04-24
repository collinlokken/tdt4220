package com.mygdx.game.model.game.component;

public class HealingComponent extends  Component
{
    private  int value;
    public  HealingComponent(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return this.value;
    }
}
