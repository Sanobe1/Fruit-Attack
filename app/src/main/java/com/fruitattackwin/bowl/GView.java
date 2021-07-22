package com.fruitattackwin.bowl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import java.util.ArrayList;

public class GView extends SurfaceView implements Runnable{
    public static int maxX = 20;
    public static int maxY = 28;
    public static float unitW = 0;
    public static float unitH = 0;
    private boolean firstTime = true;
    private boolean gameRunning = true;
    private Eye eye;
    private Thread gameThread = null;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private ArrayList<Bugs> bugs = new ArrayList<>();
    private final int BUG_INTERVAL = 50;
    private int currentTime = 0;

    public GView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        paint = new Paint();
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameRunning) {
            update();
            draw();
            checkCollision();
            checkIfNewAsteroid();
            control();
        }
    }

    private void update() {
        if(!firstTime) {
            eye.update();
            for (Bugs bug : bugs) {
                bug.update();
            }
        }
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {

            if(firstTime){
                firstTime = false;
                unitW = surfaceHolder.getSurfaceFrame().width()/maxX;
                unitH = surfaceHolder.getSurfaceFrame().height()/maxY;

                eye = new Eye(getContext());
            }

            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);

            eye.draw(paint, canvas);

            for(Bugs bugs: bugs){
                bugs.draw(paint, canvas);
            }

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkCollision(){
        for (Bugs bugs : bugs) {
            if(bugs.isCollision(eye.x, eye.y, eye.size)){
                gameRunning = false;

            }
        }
    }

    private void checkIfNewAsteroid(){
        if(currentTime >= BUG_INTERVAL){
            Bugs bug = new Bugs(getContext());
            bugs.add(bug);
            currentTime = 0;
        }else{
            currentTime ++;
        }
    }

}
