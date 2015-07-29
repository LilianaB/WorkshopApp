package com.bertha.firstdraw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by liliana on 28/07/15.
 * Class acts as view/canvas to draw ball
 */
public class DrawingTheBall extends View {

    private Bitmap bBall;
    private int x,y;

    public DrawingTheBall(Context context) {
        super(context);
        bBall = BitmapFactory.decodeResource(getResources(), R.drawable.myball);
        x = 0;
        y = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Rect rectangle = new Rect();
        rectangle.set(0,0, canvas.getWidth(), canvas.getHeight()/2);

        //set rectangle color
        Paint paintBlue = new Paint();
        paintBlue.setColor(Color.BLUE);
        paintBlue.setStyle(Paint.Style.FILL);
        canvas.drawRect(rectangle, paintBlue);

        if (x < canvas.getWidth()) {
            x += 10;
        }else {
            x = 0;
        }

        if (y < canvas.getHeight()) {
            y += 10;
        } else {
            y = 0;
        }
        canvas.drawBitmap(bBall,x,y,new Paint());
        invalidate();
    }
}
