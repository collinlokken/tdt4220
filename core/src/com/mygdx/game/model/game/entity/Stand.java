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
    public static final int height = 25;
    public static final int width = 25;

    public  Stand(PositionComponent position, VelocityComponent velocity)
    {
        super(position, new HitboxComponent(Stand.width, Stand.height, position), new DamageComponent(1), velocity);
    }
}
