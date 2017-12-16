package com.sarum.games.linesgame;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.sarum.games.linesgame.ApplicationPreferenceService;

public class GameActivity extends AppCompatActivity {
    GameSurfaceView gameSurfaceView;
    private GameConfig gameConfig;

    private int middleOfScreenX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );

        gameSurfaceView = (GameSurfaceView) findViewById(R.id.gameSurfaceView);
        gameConfig = ApplicationPreferenceService.getConfig(this);

        middleOfScreenX = Resources.getSystem().getDisplayMetrics().widthPixels / 2;

        gameSurfaceView.setOnTouchListener(new MyOnTouchListener(this)
        {
            public void MyOnDown(int posX, int posY) {
                Sprite.Direction newDir = posX < middleOfScreenX ? Sprite.Direction.LEFT : Sprite.Direction.RIGHT;
                gameConfig.getSprite().Turn(newDir);
            }
        });
    }
}
