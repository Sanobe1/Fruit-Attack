package com.fruitattackwin.bowl;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import java.util.ArrayList;

public class GView extends SurfaceView implements Runnable {
    public static int maxX = 20;
    public static int maxY = 28;
    public static float unitW = 0;
    public static float unitH = 0;
    private boolean firstTime = true;
    private boolean gameRunning = true;
    private Eye eye;
    private final Paint paint;
    private final SurfaceHolder surfaceHolder;
    private final ArrayList<Bugs> bugs = new ArrayList<>();
    private int currentTime = 0;
    private boolean backAlreadyDraw = false;
    private Bitmap bitmapSource;
    private Context appContext;

    public GView(Context context) {
        super(context);
        appContext = context;
        surfaceHolder = getHolder();
        paint = new Paint();
        bitmapSource = BitmapFactory.decodeResource(getResources(), R.drawable.back);
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameRunning) {
            update();
            draw();
            checkCollision();
            checkIfNewBugs();
            control();


        }
    }

    private void update() {
        if (!firstTime) {
            eye.update();
            for (Bugs bug : bugs) {
                bug.update();
            }
        }
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {

            if (firstTime) {
                firstTime = false;
                unitW = surfaceHolder.getSurfaceFrame().width() / maxX;
                unitH = surfaceHolder.getSurfaceFrame().height() / maxY;

                eye = new Eye(getContext());
            }

            Canvas canvas = surfaceHolder.lockCanvas();


            canvas.drawBitmap(bitmapSource, 0, 0, paint);

            eye.draw(paint, canvas);

            for (Bugs bugs : bugs) {
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

    private void checkCollision() {
        for (Bugs bugs : bugs) {
            if (bugs.isCollision(eye.x, eye.y, eye.size)) {
                gameRunning = false;

                Intent intent = new Intent(((Activity) appContext).getParent(), FinalActivity.class);
                appContext.startActivity(intent) ;
                ((Activity) appContext).finish();
            }
        }
    }

    private void checkIfNewBugs() {
        int BUG_INTERVAL = 50;
        if (currentTime >= BUG_INTERVAL) {
            Bugs bug = new Bugs(getContext());
            bugs.add(bug);
            currentTime = 0;
        } else {
            currentTime++;
        }

    }
}





