package com.mygdx.game.model.game.system;

import com.mygdx.game.model.game.Game;
import com.mygdx.game.model.game.component.DamageComponent;
import com.mygdx.game.model.game.component.HealthComponent;
import com.mygdx.game.model.game.component.HitboxComponent;
import com.mygdx.game.model.game.component.ShieldComponent;
import com.mygdx.game.model.game.entity.Entity;

import java.util.Collection;


public class HealthSystem extends AbstractSystem
{

    private  Game game;
    public  HealthSystem(Game game)
    {
        super(HealthComponent.class, DamageComponent.class, HitboxComponent.class);
        this.game = game;

    }

    @Override
    public void update(float dt)
    {
        for(Entity damageTakingEntity : this.getEntities(HealthComponent.class))
        {
            HitboxComponent hitbox = damageTakingEntity.getComponent(HitboxComponent.class);
            for(Entity damagingEntity : this.getEntities(DamageComponent.class))
            {
                DamageComponent damageComponent = damagingEntity.getComponent(DamageComponent.class);
                HitboxComponent damageHitbox = damagingEntity.getComponent(HitboxComponent.class);
                if(damageHitbox == null)
                    continue;
                if(hitbox.overlaps(damageHitbox))
                {
                    HealthComponent health = damageTakingEntity.getComponent(HealthComponent.class);
                    Collection<ShieldComponent> shields = damageTakingEntity.getComponentsOfType(ShieldComponent.class);
                    boolean isProtected = false;
                    for(ShieldComponent shield : shields)
                    {
                        if(shield.protectsAgainst(damagingEntity))
                        {
                            damageTakingEntity.removeComponent(shield);
                            isProtected = true;
                            break;
                        }
                    }
                    if(!isProtected)
                    {
                        health.decrease(damageComponent.getValue());
                        if(health.getValue() == 0 && damageTakingEntity == this.game.getPlayerEntity())
                            this.game.endGame();
                    }
                }
            }
        }
    }
}
