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
            return new LoginView();
        }
        return instance;
    }


}
