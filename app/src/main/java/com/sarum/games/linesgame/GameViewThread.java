package com.sarum.games.linesgame;

import android.graphics.Canvas;
import java.util.Calendar;

/**
 * Created by Gabrys on 09.12.2017.
 */
public class GameViewThread extends Thread {

    public GameSurfaceView gameSurfaceView;
    private boolean running = false;
    private GameConfig gameConfig;
    private GameView gameView;

    public GameViewThread(GameSurfaceView view, GameView gameV)
    {
        super();
        gameSurfaceView = view;
        gameConfig = com.sarum.games.linesgame.ApplicationPreferenceService.getConfig(view.getContext());
        gameView = gameV;
    }

    public synchronized void  setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        long startContextTimeInMs = ((Calendar.getInstance().getTimeInMillis() / 1000) + 1) * 1000;
        int gameSpeedStepInMs = 30;
        long gameNextStepTime = startContextTimeInMs;
        gameConfig.getSprite().ClearPosXYLists();

        running  =  true;

        while(running){
            Canvas canvas = gameSurfaceView.getHolder().lockCanvas();

            if (canvas != null) {
                    gameView.DrawNewSteps();
                    //gameView.DrawStep(gameConfig.getSprite().getPosX(), gameConfig.getSprite().getPosY(), gameConfig.getSprite().getColor());
                    gameView.Draw(canvas);
                    gameSurfaceView.getHolder().unlockCanvasAndPost(canvas);
            }

            try {
                gameNextStepTime = gameNextStepTime + gameSpeedStepInMs;
                long sleepTime = gameNextStepTime - Calendar.getInstance().getTimeInMillis();
                if(sleepTime > 0)
                    sleep(sleepTime);

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}