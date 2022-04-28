package com.mygdx.game.model.game.component;

public class CooldownDurationComponent extends  Component
{
    private  float duration;

    public  CooldownDurationComponent(float duration)
    {
        this.duration = duration;
    }

    public void decrease(float value)
    {
        this.duration = Math.max(this.duration - value, 0);
    }

    public float getDuration() {
        return duration;
    }
}
