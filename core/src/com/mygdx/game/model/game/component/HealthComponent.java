package com.mygdx.game.model.game.component;

public class HealthComponent extends  Component
{
    private  int value;
    private  int maxValue;

    public  HealthComponent(int value, int maxValue)
    {
        this.value = value;
        this.maxValue = maxValue;
    }

    public void decrease(int value)
    {

        if(value <= 0)
            throw new IllegalArgumentException("Invalid decreasement value");
        this.value = Math.max(0, this.value - value);
    }

    public void increase(int value)
    {
        if(value <= 0)
            throw new IllegalArgumentException("Invalid increasement value");
        this.value = Math.min(this.maxValue, this.value + value);
    }


    public int getValue() {
        return this.value;
    }
}
