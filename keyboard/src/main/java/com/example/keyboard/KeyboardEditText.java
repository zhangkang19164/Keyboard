package com.example.keyboard;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.example.keyboard.delegate.KeyboardDelegate;

/**
 * Created on 2018/8/16
 * Title:
 * Description:
 *
 * @author Android-张康
 * update 2018/8/16
 */
public class KeyboardEditText extends AppCompatEditText {
    private KeyboardDelegate mKeyboardDelegate;

    public KeyboardEditText(Context context) {
        this(context, null);
    }

    public KeyboardEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.R.attr.editTextStyle);
    }

    public KeyboardEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mKeyboardDelegate = KeyboardDelegate.create(this, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean onTouchEvent = super.onTouchEvent(event);
        mKeyboardDelegate.onTouchEvent(event);
        return onTouchEvent;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean onKeyDown = super.onKeyDown(keyCode, event);
        mKeyboardDelegate.onKeyDown(keyCode, event);
        return onKeyDown;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        boolean onKeyUp = super.onKeyUp(keyCode, event);
        mKeyboardDelegate.onKeyUp(keyCode, event);
        return onKeyUp;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        mKeyboardDelegate.setEnabled(enabled);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mKeyboardDelegate.onDetachedFromWindow();
    }
}
