package com.ancientegypt.bowl;

import android.content.Context;
import java.util.Random;

public class Bugs extends Body {
    private int radius = 2;
    private float minSpeed = (float) 0.1;
    private float maxSpeed = (float) 0.5;

    public Bugs(Context context) {
        Random random = new Random();

        bitmapId = R.drawable.bug;
        y=0;
        x = random.nextInt(GView.maxX) - radius;
        size = radius*2;
        speed = minSpeed + (maxSpeed - minSpeed) * random.nextFloat();

        init(context);
    }

    @Override
    public void update() {
        y += speed;
    }

    public boolean isCollision(float eyeX, float eyeY, float eyeSize) {
        return !(((x+size) < eyeX)||(x > (eyeX+eyeSize))||((y+size) < eyeY)||(y > (eyeY+eyeSize)));
    }
}
