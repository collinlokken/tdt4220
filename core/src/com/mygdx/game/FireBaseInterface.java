package com.mygdx.game;

import java.util.UUID;

public interface FireBaseInterface {

    public void SetOnValueChangedListener(String target);
    public void SetValueInDBb (String target, Object value);
    public void retrieveUserFromCredentials(String uname, String pwd);
    public void handleUserHighScore(UUID uuid, float score);
}
