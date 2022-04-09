package com.mygdx.game.model;


import com.badlogic.gdx.math.Rectangle;

public abstract class Model {
    private float speed;

    public abstract void interact(PlayerModel player);

    public abstract void update(float dt);

    public abstract Rectangle getCollisionBox();
}
