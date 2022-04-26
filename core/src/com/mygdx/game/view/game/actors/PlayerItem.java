package com.mygdx.game.view.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.model.game.entity.Player;
import com.mygdx.game.view.game.EntityActor;

import java.util.ArrayList;

public class PlayerItem extends EntityActor<Player>
{

    private ArrayList<Texture> textures;

    public PlayerItem(Player player, int numberOfAnimations, ArrayList<Texture> textures){
        super(numberOfAnimations, textures, player);
        this.textures = textures;
    }


    public ArrayList<Texture> getTextures(){
        return this.textures;
    }


}
