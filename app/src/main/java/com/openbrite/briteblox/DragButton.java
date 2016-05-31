package com.openbrite.briteblox;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * TODO: document your custom view class.
 */
public class DragButton extends ImageButton {
    private float mDownX;
    private float mDownY;
    private final float SCROLL_THRESHOLD = 10;
    private boolean isOnClick;
    private boolean isActivated = false;
    protected int index;

    public DragButton(Context ctx, int index) {
        super(ctx);
        this.index = index;
    }

    public DragButton(Context ctx, AttributeSet attrs, int index) {
        super(ctx, attrs);
        this.index = index;
    }

    public DragButton(Context ctx, AttributeSet attrs, int defStyleAttr, int index) {
        super(ctx, attrs, defStyleAttr);
        this.index = index;
    }

    /*public DragButton(Context ctx, AttributeSet attrs, int defStyleAttr, int defStyleRes, int index) {
        super(ctx, attrs, defStyleAttr, defStyleRes);
        this.index = index;
    }*/

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                mDownX = ev.getX();
                mDownY = ev.getY();
                isOnClick = true;
                Log.e("BriteBlox_App", "Action Down on index " + index);
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (isOnClick) {
                    Log.e("BriteBlox_App", "Action Up on index " + index);
                    isActivated = !isActivated;
                    if (isActivated)
                        setBackgroundColor(0xFFFF0000);
                    else
                        setBackgroundColor(0xFF000000);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isOnClick && (Math.abs(mDownX - ev.getX()) > SCROLL_THRESHOLD || Math.abs(mDownY - ev.getY()) > SCROLL_THRESHOLD)) {
                    Log.e("BriteBlox_App", "movement detected on index " + index);
                    isOnClick = false;
                }
                break;
            default:
                break;
        }
        return true;
    }
}
