package com.sarum.games.linesgame;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

/**
 * Created by Gabrys on 16.11.2017.
 */
public final class GameConfig {
    public static String PREFS_NAME = "GameConfigPrefsFile";

    private static String nick;
    private static int linesSize;
    private static int gameSpeed;

    private static int backgroundColor;
    private static int wallColor;

    private static Sprite sprite;

    public GameConfig(Context context) {
        loadData(context);
        sprite = new Sprite((byte) 1, 1, 1, Color.WHITE, Sprite.Direction.RIGHT);
    }

    private void loadData(Context context){
        SharedPreferences sett = context.getSharedPreferences(PREFS_NAME, 0);
        nick = sett.getString("nickKey", "Player");
        linesSize = sett.getInt("linesSizeKey", 5);
        gameSpeed = sett.getInt("gameSpeedKey", 5);
        backgroundColor = sett.getInt("backgroundColorKey", Color.BLACK);
        wallColor = sett.getInt("wallColorKey", Color.RED);
    }

    public void saveData(Context context){
        SharedPreferences.Editor sett = context.getSharedPreferences(PREFS_NAME, 0).edit();
        sett.putString("nickKey", "Player");
        sett.putInt("linesSizeKey", linesSize);
        sett.putInt("gameSpeedKey", gameSpeed);
        sett.putInt("backgroundColorKey", backgroundColor);
        sett.putInt("wallColorKey", wallColor);
        sett.commit();
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getLinesSize() {
        return linesSize;
    }

    public void setLinesSize(int linesSize) {
        this.linesSize = linesSize;
    }

    public int getGameSpeed() {
        return gameSpeed;
    }

    public void setGameSpeed(int gameSpeed) {
        this.gameSpeed = gameSpeed;
    }

    public static int getBackgroundColor() {
        return backgroundColor;
    }

    public static void setBackgroundColor(int backgroundColor) {
        GameConfig.backgroundColor = backgroundColor;
    }

    public static int getWallColor() {
        return wallColor;
    }

    public static void setWallColor(int wallColor) {
        GameConfig.wallColor = wallColor;
    }

    public static Sprite getSprite() { return sprite; }
}
