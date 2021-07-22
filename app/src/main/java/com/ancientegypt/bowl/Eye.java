package com.ancientegypt.bowl;

import android.content.Context;

public class Eye extends Body {

    public Eye(Context context) {
        bitmapId = R.drawable.eye;
        size = 5;
        x=7;
        y= GView.maxY - size - 1;
        speed = (float) 0.6;

        init(context);
    }

    @Override
    public void update() {
        if(MainActivity.isLeftPressed && x >= 0){
            x -= speed;
        }
        if(MainActivity.isRightPressed && x <= GView.maxX - 5){
            x += speed;
        }
    }
}
