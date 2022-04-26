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
    private boolean bounded;


    public  PositionComponent(double x, double y)
    {
        this(x, y, false);
    }
    public  PositionComponent(double x, double y, boolean bounded)
    {
        this.x = x;
        this.y = y;
        this.bounded = bounded;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public boolean isBounded()
    {
        return this.bounded;
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
