package com.mygdx.game.model.game.component;

public class LinearlyTimeDependentAccelerationComponent extends  Component
{
    private  float gradientX;
    private  float gradientY;
    private  float maxX;
    private  float maxY;
    public LinearlyTimeDependentAccelerationComponent(float gradientX, float gradientY, float maxX, float maxY)
    {
        this.gradientX = gradientX;
        this.gradientY = gradientY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public  double getMagnitudeX(float time)
    {
        return Math.min(this.maxX, this.gradientX*time);
    }

    public  double getMagnitudeY(float time)
    {
        return Math.min(this.maxY, this.gradientY*time);
    }
}
