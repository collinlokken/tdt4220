package com.mygdx.game.model;

import java.sql.Timestamp;

public class UserSession {
    private User user;
    Timestamp timestamp;

    public UserSession(){
        this.user = null;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setToNull(){
        this.user = null;
        this.timestamp = null;
    }

    public boolean isLoggedIn(){
        if(getUser() != null){
            return true;
        }
        return false;
    }
}
