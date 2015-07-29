package com.bertha.firstdraw;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;


public class SurfaceViewActivity extends Activity implements View.OnTouchListener {

    protected BallSurfaceView mBallView;
    private Bitmap mBitmapBall;
    private float x,y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBallView = new BallSurfaceView(this);
        mBallView.setOnTouchListener(this);
        mBitmapBall = BitmapFactory.decodeResource(getResources(), R.drawable.myball);
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

    public class BallSurfaceView extends SurfaceView implements Runnable {

        private Thread mThread = null; //handles graphics and physics, we dont want activity class
                                        // hlandling all that
        private SurfaceHolder mSurfaceHolder;
        private boolean mIsReady = false;

        public BallSurfaceView(Context context) {
            super(context);
            mSurfaceHolder = getHolder();
        }

        @Override
        public void run() {
            while (mIsReady) {
                //perform canvas drawing
                if (!mSurfaceHolder.getSurface().isValid()){
                    continue;
                }
                Canvas canvas = mSurfaceHolder.lockCanvas(); /*lock to paint, paint, unlock to show*/
                canvas.drawARGB(255, 150,150,10); /*paint canvas background*/

                //taking bitmap size into account to get touch position centered
                canvas.drawBitmap(mBitmapBall,x-(mBitmapBall.getWidth()/2),y-(mBitmapBall.getHeight()/2),null);
                mSurfaceHolder.unlockCanvasAndPost(canvas);

            }
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
