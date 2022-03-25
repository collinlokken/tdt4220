package com.mygdx.game.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.StripaSurvivor;
import com.mygdx.game.controller.Controller;

import java.util.ArrayList;
import java.util.List;

public abstract class View<T extends Controller> extends Stage {

    protected  T controller;

    protected View()
    {
        super(new ScreenViewport());
    }

    public void setController(T controller) {
        this.controller = controller;
    }

}
