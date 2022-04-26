package com.mygdx.game.model.game;

import com.mygdx.game.model.game.entity.Entity;
import com.mygdx.game.model.game.entity.Player;

public interface GameObserver
{
    void onEntityAdded(Game game, Entity entity);
    void onEntityRemoved(Game game, Entity entity);
    void onGameEnded(Game game, Player player, float score);
    void onGameStarted(Game game);
}
