package com.droidsergeant.simplegesture;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private Vibrator vibrate;
    CustomView customView;
    int initialX, initialY;
    int finalX, finalY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        customView = new CustomView(MainActivity.this);
        setContentView(customView);
        customView.setOnTouchListener(this);
        vibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN :
                initialX = (int) event.getX();
                initialY = (int) event.getY();
                //Toast.makeText(MainActivity.this, "Touched", Toast.LENGTH_SHORT).show();
                break;

            case MotionEvent.ACTION_UP :
                vibrate.vibrate(50);
                int xSum = Math.abs(initialX - finalX);
                int ySum = Math.abs(initialY - finalY);

                if (xSum < ySum){

                    if ( finalY < initialY ) {
                        Toast.makeText(MainActivity.this, "Up Gesture", Toast.LENGTH_SHORT).show();
                    }

                    if ( initialY < finalY ) {
                        Toast.makeText(MainActivity.this, "Down Gesture", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    if (initialX < finalX) {
                        Toast.makeText(MainActivity.this, "Right Gesture", Toast.LENGTH_SHORT).show();
                    }

                    if (finalX < initialX) {
                        Toast.makeText(MainActivity.this, "Left Gesture", Toast.LENGTH_SHORT).show();
                    }
                }

                break;

            case MotionEvent.ACTION_MOVE :
                finalX = (int) event.getX();
                finalY = (int) event.getY();
        }
        return true;
    }
}
