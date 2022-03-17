package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.view.View;

public abstract class Controller<T extends View> {
    protected T view;
    protected ControllerManager controllerManager;

    protected Controller(ControllerManager controllerManager, T view){
        this.view = view;
        this.view.setController(this);
        this.controllerManager = controllerManager;
    }

    public View getView(){
        return this.view;
    }


}
