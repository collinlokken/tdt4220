package com.mygdx.game.model.game.system;

import com.mygdx.game.model.game.Game;
import com.mygdx.game.model.game.component.HitboxComponent;
import com.mygdx.game.model.game.component.ShieldComponent;
import com.mygdx.game.model.game.component.ShieldConsumerComponent;
import com.mygdx.game.model.game.entity.Entity;

public class ShieldSystem extends AbstractSystem
{
    private Game game;
    public ShieldSystem(Game game)
    {
        super(ShieldComponent.class, ShieldConsumerComponent.class);
        this.game = game;
    }


    @Override
    public void update(float dt)
    {
        for(Entity shieldConsumer : this.getEntities(ShieldConsumerComponent.class))
        {
            HitboxComponent consumerHitbox = shieldConsumer.getComponent(HitboxComponent.class);
            if(consumerHitbox == null)
                continue;;
            for(ShieldComponent shield  : this.getComponentsOfType(ShieldComponent.class))
            {

                HitboxComponent shieldHitbox =  shield.getEntity().getComponent(HitboxComponent.class);
                if(shieldHitbox == null)
                    continue;
                if(shieldHitbox.overlaps(consumerHitbox))
                {
                    //System.out.println(shield.getType());
                    shield.getEntity().removeComponent(shield);
                    this.game.removeEntity(shield.getEntity());
                    if(shieldConsumer.hasComponentOfType(shield.getClass()))
                        continue;
                    this.game.addComponent(shieldConsumer, shield);
                }
            }
        }
    }
}
