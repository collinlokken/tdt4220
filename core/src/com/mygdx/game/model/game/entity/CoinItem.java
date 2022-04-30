package com.mygdx.game.model.game.entity;

import com.mygdx.game.model.game.component.HitboxComponent;
import com.mygdx.game.model.game.component.PositionComponent;
import com.mygdx.game.model.game.component.ScoreRewardComponent;
import com.mygdx.game.model.game.component.VelocityComponent;

public class CoinItem extends Entity {

    public  static  final int height = 10;
    public  static  final int width = 10;

    public CoinItem(PositionComponent position, VelocityComponent velocity)
    {
        super(position, new ScoreRewardComponent(25), new HitboxComponent(CoinItem.width, CoinItem.height, position), velocity);
    }

}
