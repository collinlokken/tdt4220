package com.mygdx.game.model.game.component;

import com.badlogic.gdx.math.Rectangle;

public class HitboxComponent extends Component
{
    private  int width;
    private  int height;

    private  PositionComponent position;


    public PositionComponent getPosition() {
        return  this.position;
    }

    public  int getWidth()
    {
        return this.width;
    }

    public  int getHeight()
    {
        return this.height;
    }

    public HitboxComponent(int width, int height, PositionComponent position)
    {
        this.width = width;
        this.height = height;
        this.position = position;
    }

    public  boolean overlaps(HitboxComponent other)
    {
        Rectangle thisRectangle = new Rectangle((int)Math.round(position.getX()), (int)Math.round(position.getY()), this.width, this.height);
        Rectangle otherRectangle = new Rectangle((int)Math.round(other.position.getX()), (int)Math.round(other.position.getY()), other.width, other.height);

        return thisRectangle.overlaps(otherRectangle);
    }



}
