package com.example.keyboard;

import android.content.Context;
import android.content.res.TypedArray;
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
    private int mKeyboardType;

    public KeyboardEditText(Context context) {
        this(context, null);
    }

    public KeyboardEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.support.v7.appcompat.R.attr.editTextStyle);
    }

    public KeyboardEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.KeyboardEditText);
        mKeyboardType = typedArray.getInteger(R.styleable.KeyboardEditText_keyboardType, -1);
        typedArray.recycle();
        mKeyboardDelegate = KeyboardDelegate.create(this, attrs);

    }

    @Override
    public void setInputType(int type) {
        super.setInputType(type);
        mKeyboardDelegate.setInputType(type);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean onTouchEvent = super.onTouchEvent(event);
        //不消费系统的触摸事件，仅对触摸事件做自己的处理
        mKeyboardDelegate.onTouchEvent(event);
        return onTouchEvent;
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

    public int getKeyboardType() {
        return mKeyboardType;
    }

    public void showKeyboard() {
        mKeyboardDelegate.showKeyboard();
    }

    public void hideKeyboard() {
        mKeyboardDelegate.hideKeyboard();
    }
}
