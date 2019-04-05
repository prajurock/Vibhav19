package com.vibhav.vibhavee.vibhav;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

public class MyGesture extends GestureDetector.SimpleOnGestureListener {

    private float initialX;
    private ViewFlipper mViewFlipper;
    private Context context;

    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initialX = touchevent.getX();
                break;
            case MotionEvent.ACTION_UP:
                float finalX = touchevent.getX();
                if (initialX > finalX) {
                    mViewFlipper.showNext();
                } else {

                    mViewFlipper.showPrevious();
                }
                break;
        }
        return false;
    }
}
