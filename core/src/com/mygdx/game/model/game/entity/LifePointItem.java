package com.mygdx.game.model.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.game.component.Component;
import com.mygdx.game.model.game.component.HealingComponent;
import com.mygdx.game.model.game.component.HitboxComponent;
import com.mygdx.game.model.game.component.PositionComponent;
import com.mygdx.game.model.game.component.VelocityComponent;

import java.util.Random;

public class LifePointItem extends Entity
{
    public LifePointItem(PositionComponent position, VelocityComponent velocity)
    {
        super(new HealingComponent(1), position, new HitboxComponent(10, 10, position), velocity);
    }
}
