package com.mygdx.game.model;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class Model {

    public abstract void interact(PlayerModel playerModel);

    public abstract void update(float dt);

    public abstract void reset();

    public abstract Rectangle getCollisionBox();

    public abstract Texture getTexture();


}
