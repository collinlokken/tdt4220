package com.mygdx.game.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class User extends Model{
    String username;
    String password;
    UUID uuid;

    public User(){}

    public User(String uname, String pwd) {
        username = uname;
        password = pwd;
        uuid = UUID.randomUUID();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("username", getUsername());
        result.put("password", getPassword());
        return result;
    }
}
