package com.bertha.firstdraw;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    private Button mStartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStartButton = (Button) findViewById(R.id.startButton);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCanvas();
            }
        });
    }

    private void startCanvas() {
        Intent intentMoveToSurfaceView = new Intent(this, SurfaceViewSecondActivity.class);
        startActivity(intentMoveToSurfaceView);
    }
}
