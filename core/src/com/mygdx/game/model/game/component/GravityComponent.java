package com.mygdx.game.model.game.component;

public class GravityComponent extends  Component
{

    private  float magnitude;
    public  GravityComponent(float magnitude)
    {
        this.magnitude = magnitude;
    }

    public  float getMagnitude()
    {
        return this.magnitude;
    }
}
