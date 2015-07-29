package com.bertha.firstdraw;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by liliana on 28/07/15.
 */
public class CanvasActivity extends Activity {

    private DrawingTheBall mViewDrawing;
    private static final String TAG = CanvasActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewDrawing = new DrawingTheBall(CanvasActivity.this);
        setContentView(mViewDrawing);

        Log.d(TAG, "in canvas");
    }

}
