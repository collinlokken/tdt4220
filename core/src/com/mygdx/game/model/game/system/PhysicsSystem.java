package com.mygdx.game.model.game.system;

import com.mygdx.game.model.game.component.BoostComponent;
import com.mygdx.game.model.game.component.GravityComponent;
import com.mygdx.game.model.game.component.PositionComponent;
import com.mygdx.game.model.game.component.VelocityComponent;
import com.mygdx.game.model.game.entity.Entity;

public class PhysicsSystem extends System
{
    public PhysicsSystem()
    {
        super(PositionComponent.class, GravityComponent.class, BoostComponent.class, VelocityComponent.class);
    }

    @Override
    public void update(float dt)
    {
        for(VelocityComponent velocityComponent : this.getComponents(VelocityComponent.class))
        {

            Entity entity = velocityComponent.getEntity();
            PositionComponent positionComponent = velocityComponent.getEntity().getComponent(PositionComponent.class);

            //TODO Clipping logic: Need to make positionComponent never go below 0 in y-direction and x-direction.
            //Alo make velocity 0 in direction of clipping.

            positionComponent.addX(velocityComponent.getX()*dt);
            positionComponent.addY((velocityComponent.getY()*dt));

            double xAccel = 0;
            double yAccel = 0;


            if(entity.hasComponentOfType(GravityComponent.class))
                yAccel -= entity.getComponent(GravityComponent.class).getMagnitude();

            if(entity.hasComponentOfType(BoostComponent.class))
                yAccel += entity.getComponent(BoostComponent.class).getMagnitude();
            java.lang.System.out.println("Y acceleration: " + yAccel);
            velocityComponent.addX(xAccel*dt);
            velocityComponent.addY(yAccel*dt);
        }

    }
}
