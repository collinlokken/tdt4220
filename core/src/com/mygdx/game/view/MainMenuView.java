package com.mygdx.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.StripaSurvivor;

public class MainMenuView extends View{
    private Texture bg;

    private static MainMenuView instance = null;

    private MainMenuView() {
        super();
        bg = new Texture("badlogic.jpg");
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

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bg, 0, 0, StripaSurvivor.WIDTH, StripaSurvivor.HEIGHT);
        sb.end();
    }

    @Override
    public void dispose() { }

}
