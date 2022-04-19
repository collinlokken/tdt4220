package com.mygdx.game.model;

import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class PlayerModel{
    private static PlayerModel instance = null;
    private float y;
    private float velocity;
    private int lifePoints;

    private float timeLeftFacemaskPowerup;
    private float timeLeftCoffeePowerup;
    private float timeLeftCookbookPowerup;
    private float timeLeftBongPowerup;

    private Rectangle collisionBox;

    private PlayerModel(){
        super();
        lifePoints = 3;
        y=0;
        velocity=0;
        collisionBox = new Rectangle(0, 0, 10, 10);
    }

    public static final PlayerModel getInstance(){
        if (instance == null){
            instance = new PlayerModel();
        }
        return instance;
    }

    public void update(float dt) {
        timeLeftFacemaskPowerup-=dt;
        if (timeLeftFacemaskPowerup > 0){
            //view should render a facemask
        }
    }

    public boolean collides(Rectangle rectangle){
        return collisionBox.overlaps(rectangle);
    }

    public boolean hasCoffeePowerup(){
        return timeLeftCoffeePowerup > 0;    }

    public void setCoffeePowerup(float duration){
        timeLeftFacemaskPowerup += duration;
    }

    public void decreaseLifePoints(){
        lifePoints -= 1;
    }
}
