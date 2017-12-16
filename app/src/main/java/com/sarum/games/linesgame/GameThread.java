package com.sarum.games.linesgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

import java.util.Arrays;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Gabrys on 09.12.2017.
 */
public class GameThread extends Thread {

    public GameSurfaceView gameSurfaceView;
    private boolean running = false;
    private GameConfig gameConfig;
    private GameView gameView;
    private byte[][] board;

    public GameThread(GameSurfaceView view)
    {
        super();
        gameSurfaceView = view;
        gameConfig = com.sarum.games.linesgame.ApplicationPreferenceService.getConfig(view.getContext());
        gameView = new GameView(this.gameConfig);

        board = new byte[gameView.getWidth()][gameView.getHeight()];
        for (byte[] row : board)
            Arrays.fill(row, (byte) 0);

        init();
    }

    public synchronized void  setRunning(boolean run) {
        running = run;
    }

    @Override
    public void run() {
        running  =  true;
        long startContextTimeInMs = ((Calendar.getInstance().getTimeInMillis() / 1000) + 1) * 1000;
        int gameSpeedStepInMs = 1000 / gameConfig.getGameSpeed();
        long gameNextStepTime = startContextTimeInMs;

//        int bmpRefreshTime = 40;
//        long bmpNextRefreshTime = startContextTimeInMs;

        while(running){
            UpdateSprites();

//            if(Calendar.getInstance().getTimeInMillis() >= bmpNextRefreshTime) {
//                bmpNextRefreshTime = bmpNextRefreshTime + bmpRefreshTime;
                Canvas canvas = gameSurfaceView.getHolder().lockCanvas();

                if (canvas != null) {
                    synchronized (gameSurfaceView.getHolder()) {
                        gameView.DrawStep(gameConfig.getSprite().getPosX(), gameConfig.getSprite().getPosY(), gameConfig.getSprite().getColor());
                        gameView.Draw(canvas);
                    }
                    gameSurfaceView.getHolder().unlockCanvasAndPost(canvas);
                }
            //}

            try {
                gameNextStepTime = gameNextStepTime + gameSpeedStepInMs;

                sleep(gameNextStepTime - Calendar.getInstance().getTimeInMillis());

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void init() {
        gameConfig.getSprite().setPosXY(gameView.getWidth() / 3, gameView.getHeight() / 2);
        gameConfig.getSprite().setAlive(true);
    }

    private void UpdateSprites() {
        if(!gameConfig.getSprite().isAlive())
        return;

            int newX = gameConfig.getSprite().getPosX();
            int newY = gameConfig.getSprite().getPosY();
            switch (gameConfig.getSprite().getDirection()) {
                case LEFT:
                    newX--;
                    break;
                case UP:
                    newY--;
                    break;
                case RIGHT:
                    newX++;
                    break;
                case DOWN:
                    newY++;
                    break;
            }

            if ((newX < 0) || (newY < 0) || (newX == board.length) || (newY == board[0].length) || (board[newX][newY] > 0)) {
                gameConfig.getSprite().setAlive(false);
            } else {
                board[newX][newY] = gameConfig.getSprite().getId();
                gameConfig.getSprite().setPosXY(newX, newY);
            }
    }

}