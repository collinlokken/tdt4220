package com.mygdx.game.model.game.system;

import com.mygdx.game.model.game.Game;
import com.mygdx.game.model.game.component.HitboxComponent;
import com.mygdx.game.model.game.component.ScoreComponent;
import com.mygdx.game.model.game.component.ScoreRewardComponent;
import com.mygdx.game.model.game.entity.Entity;

public class ScoreSystem extends AbstractSystem
{
    private Game game;
    public ScoreSystem(Game game)
    {
        super(ScoreRewardComponent.class, ScoreComponent.class);
        this.game = game;
    }


    @Override
    public void update(float dt)
    {
        for(Entity entityWithScore : this.getEntities(ScoreComponent.class))
        {
            HitboxComponent hitbox = entityWithScore.getComponent(HitboxComponent.class);
            ScoreComponent score = entityWithScore.getComponent(ScoreComponent.class);
            score.increase(11*dt);
            if(hitbox == null)
                continue;
            for(Entity scoreRewardingEntity : this.getEntities(ScoreRewardComponent.class))
            {
                ScoreRewardComponent reward = scoreRewardingEntity.getComponent(ScoreRewardComponent.class);
                HitboxComponent rewardHitbox = scoreRewardingEntity.getComponent(HitboxComponent.class);
                if(rewardHitbox == null)
                    continue;
                if(hitbox.overlaps(rewardHitbox))
                {
                    this.game.removeEntity(scoreRewardingEntity);
                    score.increase(reward.getValue());
                }
            }
        }
    }
}
