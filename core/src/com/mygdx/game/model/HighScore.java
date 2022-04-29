package com.mygdx.game.model;

import java.util.HashMap;
import java.util.Map;

public class HighScore {
    private String username;

    public String getUsername() {
        return username;
    }

    public double getScore() {
        return score;
    }

    private double score;

    public HighScore(String username, double score) {
        this.username = username;
        this.score = score;
    }

    public Map<String, Double> toMap (){
        HashMap<String, Double> result = new HashMap<>();
        result.put(getUsername(), getScore());
        return result;
    }
}
