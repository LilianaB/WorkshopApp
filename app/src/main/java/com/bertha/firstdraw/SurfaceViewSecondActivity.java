package com.bertha.firstdraw;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;


public class SurfaceViewSecondActivity extends Activity implements View.OnTouchListener {

    protected CustomSurfaceView mBallView;
    private Bitmap mBitmapBall, mBlob;
    private float x,y;
    private Sprite mSprinte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBallView = new CustomSurfaceView(this);
        mBallView.setOnTouchListener(this);
        mBitmapBall = BitmapFactory.decodeResource(getResources(), R.drawable.myball);
        mBlob = BitmapFactory.decodeResource(getResources(), R.drawable.sprite);
        x = y =0;
        setContentView(mBallView);
        //setContentView(R.layout.activity_surface_view);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBallView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBallView.resume();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        try {
            //sleep a bit before checking current event
            //avoid entering too many times to the switch
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x = event.getX();
                y = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                x = event.getX();
                y = event.getY();
                break;
        }
        return true; //to make it process more than one event at the time, not exactly a loop
    }

    /*Handles all grahpics or physics*/
    public class CustomSurfaceView extends SurfaceView implements Runnable {

        private Thread mThread = null; //handles graphics and physics, we dont want activity class handling all that
        private SurfaceHolder mSurfaceHolder;
        private boolean mIsReady = false;

        public CustomSurfaceView(Context context) {
            super(context);
            mSurfaceHolder = getHolder();
        }

        @Override
        public void run() {
            mSprinte = new Sprite(CustomSurfaceView.this,mBlob);

            while (mIsReady) {
                //perform canvas drawing
                if (!mSurfaceHolder.getSurface().isValid()){
                    continue;
                }

                Canvas canvas = mSurfaceHolder.lockCanvas(); //lock to paint, paint, unlock to show
                myDraw(canvas);
                mSurfaceHolder.unlockCanvasAndPost(canvas);

            }
        }


        protected void myDraw(Canvas canvas) {
            canvas.drawARGB(255, 150, 150, 10); /*paint canvas background*/
            canvas.drawBitmap(mBitmapBall, x - (mBitmapBall.getWidth() / 2), y - (mBitmapBall.getHeight() / 2), null);

            //config for text
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setTextSize(20);
            canvas.drawText("Example Text!", 10, 25, paint);

            mSprinte.onDraw(canvas);
        }

        //pause in case you get a call or something
        public void pause() {
            mIsReady = false;
            while(true){
                try {
                    mThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            mThread = null;
        }

        public void resume() {
            mIsReady = true;
            mThread = new Thread(this); //this refers to run method, we already have a runnable declared
            mThread.start();
        }



    }


}
