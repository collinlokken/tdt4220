package com.mygdx.game.model.game.system;

import com.mygdx.game.model.game.Game;
import com.mygdx.game.model.game.component.BoostComponent;
import com.mygdx.game.model.game.component.GravityComponent;
import com.mygdx.game.model.game.component.HitboxComponent;
import com.mygdx.game.model.game.component.LinearlyTimeDependentAccelerationComponent;
import com.mygdx.game.model.game.component.PositionComponent;
import com.mygdx.game.model.game.component.VelocityComponent;
import com.mygdx.game.model.game.entity.CoronaVirus;
import com.mygdx.game.model.game.entity.Entity;

import org.w3c.dom.html.HTMLLIElement;

public class PhysicsSystem extends AbstractSystem
{
    private Game game;
    public PhysicsSystem(Game game)
    {
        super(PositionComponent.class);
        this.game = game;
    }

    private float elapsedTime = 0.0f;

    @Override
    public void update(float dt)
    {
        elapsedTime += dt;
        for(PositionComponent positionComponent : this.getComponents(PositionComponent.class))
        {

            Entity entity = positionComponent.getEntity();
            VelocityComponent velocityComponent = entity.getComponent(VelocityComponent.class);
            HitboxComponent hitbox = entity.getComponent(HitboxComponent.class);

            positionComponent.addX(velocityComponent.getX()*dt);
            positionComponent.addY((velocityComponent.getY()*dt));

                //TODO Later if wanted, add x acceleration components things.
                double xAccel = 0;
                double yAccel = 0;

                if(entity.hasComponentOfType(GravityComponent.class))
                    yAccel -= entity.getComponent(GravityComponent.class).getMagnitude();

                if(entity.hasComponentOfType(BoostComponent.class))
                    yAccel += entity.getComponent(BoostComponent.class).getMagnitude();
                if(entity.hasComponentOfType(LinearlyTimeDependentAccelerationComponent.class))
                {
                    LinearlyTimeDependentAccelerationComponent acceleration = entity.getComponent(LinearlyTimeDependentAccelerationComponent.class);
                    xAccel += acceleration.getMagnitudeX(this.elapsedTime);
                    yAccel += acceleration.getMagnitudeY(this.elapsedTime);
                }

            velocityComponent.addX(xAccel*dt);
            velocityComponent.addY(yAccel*dt);

            if(positionComponent.isBounded() && hitbox != null)
            {
                if(((velocityComponent.getY() < 0 && positionComponent.getY() <= 0) || (velocityComponent.getY() > 0 && positionComponent.getY() + hitbox.getHeight() >= this.game.getHeight())))
                {
                    velocityComponent.setY(0);
                    yAccel = 0;
                }
                // Also X
            }
        }

    }
}
