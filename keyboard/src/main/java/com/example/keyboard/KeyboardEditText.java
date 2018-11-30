package com.example.keyboard;

import android.content.Context;
import android.graphics.Rect;
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
    public void setInputType(int type) {
        super.setInputType(type);
        mKeyboardDelegate.setInputType(type);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mKeyboardDelegate.onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mKeyboardDelegate.onKeyDown(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (mKeyboardDelegate.onKeyUp(keyCode, event)) {
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        mKeyboardDelegate.onFocusChanged(focused);
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

    public void showKeyboard() {
        mKeyboardDelegate.showKeyboard();
    }

    public void hideKeyboard() {
        mKeyboardDelegate.hideKeyboard();
    }
}
