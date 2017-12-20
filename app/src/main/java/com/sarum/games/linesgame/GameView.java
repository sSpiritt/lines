package com.sarum.games.linesgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import com.sarum.games.linesgame.ApplicationPreferenceService;

import java.util.List;

/**
 * Created by Gabrys on 09.12.2017.
 */
public final class GameView {
    private GameSurfaceView gameSurfaceView;
    private GameConfig gameConfig;
    private Bitmap bmpGame;
    private Canvas canvasGame;
    private int lineSize;
    private int width;
    private int height;

    private int startX;
    private int startY;

    public GameView(Context context, GameSurfaceView gameSurfaceV) {
        this(ApplicationPreferenceService.getConfig(context), gameSurfaceV);
    }

    public GameView(GameConfig gameConfig, GameSurfaceView gameSurfaceV) {
        this.gameSurfaceView = gameSurfaceV;
        this.gameConfig = gameConfig;
        bmpGame = Bitmap.createBitmap(
                Resources.getSystem().getDisplayMetrics().widthPixels,
                Resources.getSystem().getDisplayMetrics().heightPixels,
                Bitmap.Config.ARGB_8888);

        lineSize = gameConfig.getLinesSize() * 4;

        width = (bmpGame.getWidth() / lineSize) - 1;
        height = (bmpGame.getHeight() / lineSize) - 1;

        startX = (bmpGame.getWidth() - width * lineSize) / 2;
        startY = (bmpGame.getHeight() - height * lineSize) / 2;

        canvasGame = new Canvas(bmpGame);
        DrawBackground();
    }

    public void Draw(Canvas canvas){
            canvas.drawBitmap(bmpGame, 0, 0, null);
    }

    public void DrawNewSteps() {
        Sprite sprite = gameConfig.getSprite();
        if(sprite.newPosXList.size() == 0)
            return;
        for(int idx = 0; idx < sprite.newPosXList.size(); idx++)
            DrawStep(sprite.newPosXList.get(idx), sprite.newPosYList.get(idx), sprite.getColor());
        sprite.ClearPosXYLists();
    }

    public void DrawStep(int x, int y, int color){
        Paint myPaint = new Paint();
        myPaint.setColor(color);
        if(gameConfig.getLinesAsSquares()) {
            canvasGame.drawRect(startX + x * lineSize + 1,
                    startY + y * lineSize + 1,
                    startX + ++x * lineSize - 1,
                    startY + ++y * lineSize - 1,
                    myPaint);
        }
        else
        {
            canvasGame.drawRect(startX + x * lineSize,
                    startY + y * lineSize,
                    startX + ++x * lineSize,
                    startY + ++y * lineSize,
                    myPaint);
        }
    }

    public void DrawBackground(){
        Paint myPaint = new Paint();
        myPaint.setColor(gameConfig.getWallColor());
        canvasGame.drawRect(0, 0, bmpGame.getWidth(), bmpGame.getHeight(), myPaint);
        myPaint.setColor(gameConfig.getBackgroundColor());
        canvasGame.drawRect(startX, startY, startX + width * lineSize, startY + height * lineSize, myPaint);
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public static void DrawSign(int xPos, int yPos, char c, Canvas canvas) {
        int gridWidth = 10;
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        switch (c) {
            case '0':
                drawGrid(xPos, yPos, gridWidth, canvas, paint);
                drawGrid(++xPos, yPos, gridWidth, canvas, paint);
                drawGrid(++xPos, yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(--xPos, yPos, gridWidth, canvas, paint);
                drawGrid(--xPos, yPos, gridWidth, canvas, paint);
                drawGrid(xPos, --yPos, gridWidth, canvas, paint);
                drawGrid(xPos, --yPos, gridWidth, canvas, paint);
                drawGrid(xPos, --yPos, gridWidth, canvas, paint);
                drawGrid(xPos, --yPos, gridWidth, canvas, paint);
                break;
            case '1':
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(++xPos, --yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(++xPos, yPos, gridWidth, canvas, paint);
                drawGrid(xPos-2, yPos, gridWidth, canvas, paint);
                break;
            case '2':
                drawGrid(xPos, yPos, gridWidth, canvas, paint);
                drawGrid(++xPos, yPos, gridWidth, canvas, paint);
                drawGrid(++xPos, yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(--xPos, yPos, gridWidth, canvas, paint);
                drawGrid(--xPos, yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(++xPos, yPos, gridWidth, canvas, paint);
                drawGrid(++xPos, yPos, gridWidth, canvas, paint);
                break;
            case '3':
                drawGrid(xPos, yPos, gridWidth, canvas, paint);
                drawGrid(++xPos, yPos, gridWidth, canvas, paint);
                drawGrid(++xPos, yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos-1, yPos, gridWidth, canvas, paint);
                drawGrid(xPos-2, yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(--xPos, yPos, gridWidth, canvas, paint);
                drawGrid(--xPos, yPos, gridWidth, canvas, paint);
                break;
            case '4':
                drawGrid(xPos, yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(++xPos, yPos, gridWidth, canvas, paint);
                drawGrid(++xPos, yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, yPos-3, gridWidth, canvas, paint);
                drawGrid(xPos, yPos-4, gridWidth, canvas, paint);
                break;
            case '5':
                drawGrid(xPos+1, yPos, gridWidth, canvas, paint);
                drawGrid(xPos+2, yPos, gridWidth, canvas, paint);
                drawGrid(xPos, yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(++xPos, yPos, gridWidth, canvas, paint);
                drawGrid(++xPos, yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(--xPos, yPos, gridWidth, canvas, paint);
                drawGrid(--xPos, yPos, gridWidth, canvas, paint);
                break;
            case '6':
                drawGrid(xPos+1, yPos, gridWidth, canvas, paint);
                drawGrid(xPos+2, yPos, gridWidth, canvas, paint);
                drawGrid(xPos, yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(++xPos, yPos, gridWidth, canvas, paint);
                drawGrid(++xPos, yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(--xPos, yPos, gridWidth, canvas, paint);
                drawGrid(--xPos, yPos, gridWidth, canvas, paint);
                drawGrid(xPos, --yPos, gridWidth, canvas, paint);
                break;
            case '7':
                drawGrid(xPos, yPos, gridWidth, canvas, paint);
                drawGrid(++xPos, yPos, gridWidth, canvas, paint);
                drawGrid(++xPos, yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                break;
            case '8':
                drawGrid(xPos, yPos, gridWidth, canvas, paint);
                drawGrid(++xPos, yPos, gridWidth, canvas, paint);
                drawGrid(++xPos, yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(--xPos, yPos, gridWidth, canvas, paint);
                drawGrid(--xPos, yPos, gridWidth, canvas, paint);
                drawGrid(xPos, --yPos, gridWidth, canvas, paint);
                drawGrid(xPos, --yPos, gridWidth, canvas, paint);
                drawGrid(xPos, --yPos, gridWidth, canvas, paint);
                drawGrid(xPos, --yPos, gridWidth, canvas, paint);
                drawGrid(++xPos, yPos+2, gridWidth, canvas, paint);
                break;
            case '9':
                drawGrid(xPos, yPos, gridWidth, canvas, paint);
                drawGrid(++xPos, yPos, gridWidth, canvas, paint);
                drawGrid(++xPos, yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(--xPos, yPos, gridWidth, canvas, paint);
                drawGrid(xPos, yPos-2, gridWidth, canvas, paint);
                drawGrid(--xPos, yPos, gridWidth, canvas, paint);
                drawGrid(xPos, yPos-2, gridWidth, canvas, paint);
                drawGrid(xPos, yPos-3, gridWidth, canvas, paint);

                break;
            case ':':
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, yPos+2, gridWidth, canvas, paint);
                break;
            case '.':
                drawGrid(xPos, yPos+4, gridWidth, canvas, paint);
                break;
            case '°':
                drawGrid(++xPos, yPos, gridWidth, canvas, paint);
                drawGrid(++xPos, yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(--xPos, yPos, gridWidth, canvas, paint);
                break;
            case 'C':
                drawGrid(xPos, yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(xPos, ++yPos, gridWidth, canvas, paint);
                drawGrid(++xPos, yPos, gridWidth, canvas, paint);
                drawGrid(xPos, yPos-4, gridWidth, canvas, paint);
                drawGrid(++xPos, yPos, gridWidth, canvas, paint);
                drawGrid(xPos, yPos-4, gridWidth, canvas, paint);
                break;
        }
    }

    private static void drawGrid(int x, int y, float width, Canvas canvas, Paint paint) {
       canvas.drawRect(--x * width + 1, --y * width + 1, (++x) * width - 1, (++y) * width - 1, paint);
    }
}
