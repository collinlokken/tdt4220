package com.mygdx.game.view.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.view.game.spriteActors.StripaSurvivorActor;

import java.util.ArrayList;

public class PlayerItem extends StripaSurvivorActor {

    private Vector2 position;
    private ArrayList<Texture> textures;

    public PlayerItem(int x, int y, int width, int height, int numberOfAnimations, ArrayList<Texture> textures){
        super(x, y, width, height, numberOfAnimations, textures);
        this.position = new Vector2(x, y);
        this.textures = textures;
    }

    @Override
    public void setActorPosition(float x, float y) {
        super.setActorPosition(x+this.position.x, y+this.position.y);
    }

    public ArrayList<Texture> getTextures(){
        return this.textures;
    }


}
