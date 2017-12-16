package com.sarum.games.linesgame;

import android.graphics.Color;

/**
 * Created by Gabrys on 11.12.2017.
 */
public class Sprite {
    public enum Direction {LEFT, UP, RIGHT, DOWN};
    private Direction direction;
    private byte id;
    private int posX;
    private int posY;
    private int color;
    private boolean alive = true;

    public Sprite(byte id, int x, int y, int lineColor, Direction dir){
        this.id = id;
        this.posX = x;
        this.posY = y;
        this.color = lineColor;
        this.direction = dir;
    }

    public byte getId() {
        return id;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setPosXY(int X, int Y) {
        this.posX = X;
        this.posY = Y;
    }

    public int getColor() {
        return color;
    }

    public Direction getDirection() {
        return this.direction;
        }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void Turn(Direction dir) {
        switch (this.direction) {
            case LEFT: this.direction = (dir == Direction.LEFT) ? Direction.DOWN : Direction.UP;
                break;
            case UP: this.direction = (dir == Direction.LEFT) ? Direction.LEFT : Direction.RIGHT;
                break;
            case RIGHT: this.direction = (dir == Direction.LEFT) ? Direction.UP : Direction.DOWN;
                break;
            case DOWN: this.direction = (dir == Direction.LEFT) ? Direction.RIGHT : Direction.LEFT;
                break;
        }
    }
}
