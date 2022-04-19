package com.mygdx.game.controller;

import com.mygdx.game.view.View;

public abstract class Controller<T extends View> {
    protected T view;

    protected Controller( T view){
        this.view = view;
        this.view.setController(this);
    }

    public View getView(){
        return this.view;
    }

    public abstract void update(float dt);

}
