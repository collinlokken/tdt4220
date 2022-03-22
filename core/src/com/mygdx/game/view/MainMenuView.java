package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.StripaSurvivor;
import com.mygdx.game.controller.MainMenuController;

public class MainMenuView extends View<MainMenuController>{
    private Stage stage;
    private Image background;

    private static MainMenuView instance = null;

    private MainMenuView() {
        super();
        this.stage = new Stage();
        this.background = new Image(new Texture("bg.png"));
        this.stage.addActor(this.background);
        Music music = Gdx.audio.newMusic(Gdx.files.internal("kahoot_bg.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
    }

    public static final MainMenuView getInstance(){
        if (instance == null){
            return new MainMenuView();
        }
        return instance;
    }



}
