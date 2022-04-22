package com.mygdx.game.view.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.view.game.spriteActors.StripaSurvivorActor;

import java.util.ArrayList;

public class PlayerItem extends StripaSurvivorActor {

    private Vector2 position;

    public PlayerItem(int x, int y, int width, int height, int numberOfAnimations, ArrayList<Texture> textures){
        super(x, y, width, height, numberOfAnimations, new ArrayList<PlayerItem>(), textures);
        this.position = new Vector2(x, y);

    }

    @Override
    public void setActorPosition(float x, float y) {
        super.setActorPosition(x+this.position.x, y+this.position.y);
    }


}
