package com.mygdx.game.model.game.entity;

import com.mygdx.game.model.game.component.HitboxComponent;
import com.mygdx.game.model.game.component.PositionComponent;
import com.mygdx.game.model.game.component.ScoreRewardComponent;

public class CoinItem extends Entity {
    public  CoinItem(PositionComponent position)
    {
        super(position, new ScoreRewardComponent(25), new HitboxComponent(10, 10, position));
    }

}
