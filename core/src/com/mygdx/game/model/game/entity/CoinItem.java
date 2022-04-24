package com.mygdx.game.model.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.game.component.HitboxComponent;
import com.mygdx.game.model.game.component.PositionComponent;
import com.mygdx.game.model.game.component.ScoreRewardComponent;
import com.mygdx.game.model.game.entity.Entity;

import java.util.Random;

public class CoinItem extends Entity {
    public  CoinItem(PositionComponent position)
    {
        super(position, new ScoreRewardComponent(1), new HitboxComponent(10, 10, position));
    }

}
