package com.mygdx.game;

public interface FireBaseInterface {

    public void SetOnValueChangedListener(String target);
    public void SetValueInDBb (String target, Object value);
    public void retrieveUserFromCredentials(String uname, String pwd);
}
