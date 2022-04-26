package com.mygdx.game.view.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.model.game.entity.CoronaVirus;
import com.mygdx.game.view.game.EntityActor;

import java.util.ArrayList;

public class CoronaVirusActor extends EntityActor<CoronaVirus>
{

    public CoronaVirusActor(CoronaVirus entity)
    {
        super(1, new ArrayList<Texture>(), entity);
      //  super(numberOfAnimations, textures, entity);
    }
}
