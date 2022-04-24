package com.mygdx.game.model.game.component;

public class PositionComponent extends Component
{
    public double getX()
    {
        return this.x;
    }

    public  double getY()
    {
        return this.y;
    }

    private  double x;
    private  double y;

    public  PositionComponent(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void addX(double delta)
    {
        this.x += delta;
    }
    public  void addY(double delta)
    {
        this.y += delta;
    }
}
