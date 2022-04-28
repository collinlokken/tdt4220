package com.mygdx.game.model.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.game.component.DamageComponent;
import com.mygdx.game.model.game.component.HitboxComponent;
import com.mygdx.game.model.game.component.PositionComponent;
import com.mygdx.game.model.game.component.VelocityComponent;
import com.mygdx.game.model.game.entity.Entity;

public class Stand extends Entity
{
    public  Stand(PositionComponent position, VelocityComponent velocity)
    {
        super(position, new HitboxComponent(30, 20, position), new DamageComponent(1), velocity);
    }
}
