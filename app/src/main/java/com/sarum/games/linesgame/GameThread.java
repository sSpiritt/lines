package com.sarum.games.linesgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

/**
 * Created by Gabrys on 09.12.2017.
 */
public class GameThread extends Thread {

    public GameSurfaceView gameSurfaceView;
    private boolean running = false;
    private GameConfig gameConfig;
    private byte[][] board;

    public GameThread(GameSurfaceView view, int boardWidth, int boardHeight)
    {
        super();
        gameSurfaceView = view;
        gameConfig = com.sarum.games.linesgame.ApplicationPreferenceService.getConfig(view.getContext());

        board = new byte[boardWidth][boardHeight];
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
        int gameSpeedStepInMs = 1000 / (gameConfig.getGameSpeed() * (11 - gameConfig.getLinesSize()));
        long gameNextStepTime = startContextTimeInMs;

        while(running){
            UpdateSprites();

            try {
                gameNextStepTime = gameNextStepTime + gameSpeedStepInMs;
                long sleepTime = gameNextStepTime - Calendar.getInstance().getTimeInMillis();
                if (sleepTime > 0)
                    sleep(sleepTime);

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void init() {
        Sprite sprite = gameConfig.getSprite();
        sprite.setPosXY(board.length / 3, board[0].length / 2);
        sprite.setDirection(Sprite.Direction.RIGHT);
        sprite.setAlive(true);
    }

    private synchronized void UpdateSprites() {
        Sprite sprite = gameConfig.getSprite();
        if(!sprite.isAlive())
        return;

            int newX = sprite.getPosX();
            int newY = sprite.getPosY();
            switch (sprite.getDirection()) {
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

//        Lock lock =;
//
//            lock.tryLock();
            if ((newX < 0) || (newY < 0) || (newX == board.length) || (newY == board[0].length) || (board[newX][newY] > 0)) {
                sprite.setAlive(false);
            } else {
                board[newX][newY] = sprite.getId();
                sprite.AddNewPosXYToLists(newX, newY);
            }

           // lock.unlock();
    }

}