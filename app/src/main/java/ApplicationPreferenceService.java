package com.sarum.games.linesgame;

import android.content.Context;

/**
 * Created by Gabrys on 09.12.2017.
 */
public final class ApplicationPreferenceService {
    private static GameConfig gameConfig;

    private ApplicationPreferenceService (){}

    public  static GameConfig getConfig(Context context) {
        if(gameConfig == null){
            gameConfig = new GameConfig(context);
        }

        return gameConfig;
    }
}
