package com.mygdx.game.model;

import com.badlogic.gdx.math.Rectangle;

public class PlayerModel{
    private static PlayerModel instance = null;
    private float y;
    private float velocity;

    private float timeLeftFacemaskPowerup;
    private float timeLeftCoffeePowerup;
    private float timeLeftCookbookPowerup;
    private float timeLeftBongPowerup;

    private Rectangle collisionBox;

    private PlayerModel(){
        super();
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
}
