package com.mygdx.game.view.mainMenu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.StripaSurvivor;
public class Background extends Actor
{
    Texture texture = new Texture("bg.png");
    @Override
    public  void draw(Batch batch, float parentAlpha)
    {
        batch.draw(texture, 0,0);
    }
}
