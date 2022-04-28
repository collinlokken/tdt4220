package com.mygdx.game.model.game.system;

import com.mygdx.game.model.game.Game;
import com.mygdx.game.model.game.component.HitboxComponent;
import com.mygdx.game.model.game.component.ShieldComponent;
import com.mygdx.game.model.game.component.ShieldConsumerComponent;
import com.mygdx.game.model.game.component.ShieldRewardComponent;
import com.mygdx.game.model.game.entity.Entity;

public class ShieldSystem extends AbstractSystem
{
    private Game game;
    public ShieldSystem(Game game)
    {
        super(ShieldConsumerComponent.class, ShieldRewardComponent.class);
        this.game = game;
    }


    @Override
    public void update(float dt)
    {
        for(Entity shieldConsumer : this.getEntities(ShieldConsumerComponent.class))
        {
            HitboxComponent consumerHitbox = shieldConsumer.getComponent(HitboxComponent.class);
            if(consumerHitbox == null)
                continue;
            for(ShieldRewardComponent shieldReward  : this.getComponentsOfType(ShieldRewardComponent.class))
            {
                HitboxComponent shieldRewardHitbox =  shieldReward.getEntity().getComponent(HitboxComponent.class);
                if(shieldRewardHitbox == null)
                    continue;
                if(shieldRewardHitbox.overlaps(consumerHitbox))
                {
                    ShieldComponent shield = shieldReward.reward();
                    this.game.removeEntity(shieldReward.getEntity());
                    if(shieldConsumer.hasComponentOfType(shield.getClass()))
                        continue;
                    this.game.addComponent(shieldConsumer, shield);
                }
            }
        }
    }
}
