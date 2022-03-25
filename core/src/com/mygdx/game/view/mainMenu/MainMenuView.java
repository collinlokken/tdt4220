package com.mygdx.game.view.mainMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.controller.MainMenuController;
import com.mygdx.game.view.View;

public class MainMenuView extends View<MainMenuController> {

    private static MainMenuView instance = null;

    private MainMenuView() {
        super();
        this.addActor(new Background());
        ImageButton playButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("play.png"))));
        playButton.setPosition(500, 500);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                System.out.println("Hello");
            }
        });
        this.addActor(playButton);
        Music music = Gdx.audio.newMusic(Gdx.files.internal("kahoot_bg.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
    }

    public static final MainMenuView getInstance(){
        if (instance == null){
            instance = new MainMenuView();
        }
        return instance;
    }



}
