package com.mygdx.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.StripaSurvivor;
import com.mygdx.game.controller.MainMenuController;

public class MainMenuView extends View<MainMenuController>{
    private UIComponent bg;
    private UIComponent playButton;
    private UIComponent helpButton;
    private UIComponent badge;

    private static MainMenuView instance = null;

    private MainMenuView() {
        super();
        this.bg = new UIComponent(0, 0, StripaSurvivor.WIDTH, StripaSurvivor.HEIGHT, new Texture("white_bg.jpg"), true);
        this.playButton = new UIComponent(StripaSurvivor.WIDTH/2, StripaSurvivor.HEIGHT/2, 64, 64, new Texture("play_button.png"), true);
        this.helpButton = new UIComponent(StripaSurvivor.WIDTH/2-200, StripaSurvivor.HEIGHT/2-200, 64, 64, new Texture("help.png"), true);
        this.badge = new UIComponent(StripaSurvivor.WIDTH/2+200, StripaSurvivor.HEIGHT/2+200, 342, 73, new Texture("badge.png"), true);
        this.addComponents(this.bg, this.playButton, this.helpButton, this.badge);
    }

    public static final MainMenuView getInstance(){
        if (instance == null){
            return new MainMenuView();
        }
        return instance;
    }

    @Override
    public void handleInput() { }

    @Override
    public void update(float dt) { }


}
