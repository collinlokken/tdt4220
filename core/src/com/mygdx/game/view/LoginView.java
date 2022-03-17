package com.mygdx.game.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.StripaSurvivor;

import jdk.tools.jaotc.binformat.macho.MachOSegment;

public class LoginView extends View{
    private static LoginView instance = null;

    private LoginView(){
        super();
    }

    public static final LoginView getInstance(){
        if (instance == null){
            instance = new LoginView();
        }
        return instance;
    }

    @Override
    public void handleInput() { }

    @Override
    public void update(float dt) { }

    @Override
    public void render(SpriteBatch sb) {
    }

    @Override
    public void dispose() { }

}
