package com.sarum.games.linesgame;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabrys on 09.12.2017.
 */
public class GameSurfaceView extends SurfaceView {
    private GameSurfaceView gameSurfaceView;
    private SurfaceHolder surfaceHolder;
    public GameThread gameThread;
    public GameViewThread gameViewThread;
    public GameView gameView;

    public GameSurfaceView(Context context) {
        super(context);
        init(context);
    }

    public GameSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GameSurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
    }

    private void init(Context context) {
        gameView = new GameView(context, gameSurfaceView);
        gameSurfaceView = (GameSurfaceView) findViewById(R.id.gameSurfaceView);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                gameViewThread = new GameViewThread(gameSurfaceView, gameView);
                gameViewThread.start();

                gameThread = new GameThread(gameSurfaceView, gameView.getWidth(), gameView.getHeight());
                gameThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;

                gameThread.setRunning(false);
                while (retry) {
                    try {
                        gameThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }

                retry = true;

                gameViewThread.setRunning(false);
                while (retry) {
                    try {
                        gameViewThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                    }
                }
            }

        });
    }

}
