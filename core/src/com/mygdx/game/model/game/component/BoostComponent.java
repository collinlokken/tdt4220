package com.mygdx.game.model.game.component;

public class BoostComponent extends Component
{
    private  float magnitude;
    public  BoostComponent(float magnitude)
    {
        this.magnitude = magnitude;
    }

    public  float getMagnitude()
    {
        return  this.magnitude;
    }
}
