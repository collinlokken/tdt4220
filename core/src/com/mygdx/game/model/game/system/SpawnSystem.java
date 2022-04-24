package com.mygdx.game.model.game.system;

import com.mygdx.game.model.Game;
import com.mygdx.game.model.game.component.HitboxComponent;
import com.mygdx.game.model.game.component.PositionComponent;
import com.mygdx.game.model.game.entity.CoffeeCup;
import com.mygdx.game.model.game.entity.Entity;

import java.util.Random;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class SpawnSystem extends System
{
    private float time;

    private  Game game;
    private Random random;
    public  SpawnSystem(Game game)
    {
        super(HitboxComponent.class);
        this.game = game;
        this.time = 0;
        this.random = new Random();
    }



    @Override
    public void update(float dt)
    {
        this.time += dt;
        for(HitboxComponent component : this.getComponents(HitboxComponent.class))
        {
            if(component.getPosition().getX() < component.getWidth())
                game.removeEntity(component.getEntity());

        }
      //  if((Math.round(this.time*10)) % 10 == 0)
            //this.game.addEntity(this.getRandomItem());


        //! TODO Add use this.game.addEntity(new Entity) Use a Math random to decide whether to new CoronaVirus(), new CoronaVirusShield() etc
    }

    private void getRandomItem()
    {
        int nItems = 6;
        int choice = this.random.nextInt(nItems);
        //PositionComponent component = new PositionComponent(random.nextInt() - 1, )

    }
}
