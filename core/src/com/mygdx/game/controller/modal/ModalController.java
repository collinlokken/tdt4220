package com.mygdx.game.controller.modal;

import com.mygdx.game.controller.Controller;
import com.mygdx.game.view.modal.ModalView;

public class ModalController extends Controller<ModalView> {
    private static ModalController instance = null;

    protected ModalController() { super(ModalView.getInstance()); }

    public static final ModalController getInstance(){
        if (instance == null){
            instance = new ModalController();
        }
        return instance;
    }

    @Override
    public void update(float dt) {

    }
}
