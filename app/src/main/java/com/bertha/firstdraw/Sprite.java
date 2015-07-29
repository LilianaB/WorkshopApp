package com.bertha.firstdraw;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by liliana on 28/07/15.
 */
public class Sprite {

    private int mX, mY;
    private int mXSpeed, mYSpeed;
    private int mHeight,mWidth;
    private Bitmap mBitmap;
    private SurfaceViewSecondActivity.CustomSurfaceView mCustomView;
    private int mCurrentFrame = 0;
    private int mDirection=0;

    public Sprite(SurfaceViewSecondActivity.CustomSurfaceView customSurfaceView, Bitmap mBlob) {
        mCustomView = customSurfaceView;
        mBitmap = mBlob;
        mHeight = mBitmap.getHeight()/4; //4 rows
        mWidth = mBitmap.getWidth()/10; //4 columns
        mX = mY = 0;
        mXSpeed = 5;
        mYSpeed = 0;
    }

    public void onDraw(Canvas canvas) {
        update();
        int srX = mCurrentFrame * mWidth;
        int srcY = mDirection * mHeight;
        Rect src = new Rect(srX,srcY, srX + mWidth, srcY+mHeight); //section of sprintsheet that will be cutout
        Rect dst = new Rect (mX, mY, mX+mWidth, mY+mHeight); // scale from x,y to second parametersh
        canvas.drawBitmap(mBitmap,src,dst,null);
    }

    private void update() {

        //facing down = 0
        if (mX > mCustomView.getWidth() - mWidth - mXSpeed){
            mXSpeed = 0;
            mYSpeed = 5;
            mDirection = 0;
        }
        //going left = 1
        if (mY > mCustomView.getHeight() - mHeight -mYSpeed) {
            mXSpeed = -5;
            mYSpeed = 0;
            mDirection = 1;
        }
        //facing up = 2
        if (mX + mXSpeed < 0){
            mX = 0;
            mXSpeed = 0;
            mYSpeed = -5;
            mDirection = 2;

        }
        //facing right = 3
        if (mY + mYSpeed < 0){
            mY = 0;
            mYSpeed = 0;
            mXSpeed = 5;
            mDirection = 3;

        }

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mCurrentFrame = ++mCurrentFrame % 10;
        mX += mXSpeed;
        mY += mYSpeed;
    }
}
