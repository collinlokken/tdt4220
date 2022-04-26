package com.mygdx.game.view.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.model.game.entity.Player;
import com.mygdx.game.view.game.spriteActors.GameActor;

import java.util.ArrayList;

public class PlayerItem extends GameActor<Player>
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
