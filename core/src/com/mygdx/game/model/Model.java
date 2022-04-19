package com.mygdx.game.model;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class Model {
    protected float speed;

    public abstract void interact(PlayerModel player);

    public abstract void update(float dt);

    public abstract Rectangle getCollisionBox();

    public abstract Texture getTexture();
}
