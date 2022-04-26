package com.mygdx.game.model.game.component;

public class ScoreComponent extends Component
{
    private float value;
    public  ScoreComponent(int value)
    {
        this.value = value;
    }

    public float getValue() {
        return this.value;
    }

    public void decrease(float value)
    {
        if(value <= 0)
            throw new IllegalArgumentException("Invalid decreasement value");
        this.value = Math.max(0, this.value - value);
    }

    public void increase(float value)
    {
        if(value <= 0)
            throw new IllegalArgumentException("Invalid increasement value");
        this.value += value;
    }
}
