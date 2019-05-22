package com.example.keyboard.dialog;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager;

import com.example.keyboard.KeyboardEditText;

/**
 * Created on 2018/8/16
 * Title:
 * Description:
 *
 * @author Android-张康
 * update 2018/8/16
 */
public class KeyboardDialog extends AppCompatDialog {
    private KeyboardDialogDelegate mKeyboardDialogDelegate;
    private final KeyEvent.Callback mKeyEventCallback;

    public KeyboardDialog(Context context) {
        this(context, null);
    }


    public KeyboardDialog(Context context, KeyEvent.Callback keyEventCallback) {
        super(context, selectSystemTheme(context));
        mKeyEventCallback = keyEventCallback;
        initDockWindow();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getKeyboardDialogDelegate().onCreate(savedInstanceState);
    }

    @Override
    public boolean dispatchTouchEvent(@NonNull MotionEvent ev) {
        return getKeyboardDialogDelegate().dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev);
//        if (ev.isWithinBoundsNoHistory(mBounds.left, mBounds.top,
//                mBounds.right - 1, mBounds.bottom - 1)) {
//            return super.dispatchTouchEvent(ev);
//        } else {
//            MotionEvent temp = ev.clampNoHistory(mBounds.left, mBounds.top,
//                    mBounds.right - 1, mBounds.bottom - 1);
//            boolean handled = super.dispatchTouchEvent(temp);
//            temp.recycle();
//            return handled;
//        }
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (mKeyEventCallback != null && mKeyEventCallback.onKeyDown(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, @NonNull KeyEvent event) {
        if (mKeyEventCallback != null && mKeyEventCallback.onKeyLongPress(keyCode, event)) {
            return true;
        }
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, @NonNull KeyEvent event) {
        if (mKeyEventCallback != null && mKeyEventCallback.onKeyUp(keyCode, event)) {
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int count, @NonNull KeyEvent event) {
        if (mKeyEventCallback != null && mKeyEventCallback.onKeyMultiple(keyCode, count, event)) {
            return true;
        }
        return super.onKeyMultiple(keyCode, count, event);
    }


    private void initDockWindow() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.setTitle("KeyboardDialog");
        lp.gravity = Gravity.BOTTOM;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(lp);
        int windowSetFlags = WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        int windowModFlags = WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                WindowManager.LayoutParams.FLAG_DIM_BEHIND;

        windowSetFlags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        getWindow().setFlags(windowSetFlags, windowModFlags);
    }


    public void focusIn(@NonNull KeyboardEditText editText) {
        getKeyboardDialogDelegate().focusIn(editText);
    }

    public void focusOut(@NonNull KeyboardEditText editText) {
        getKeyboardDialogDelegate().focusOut(editText);
    }


    private static int selectSystemTheme(Context context) {
        int targetSdkVersion = context.getApplicationInfo().targetSdkVersion;
        if (targetSdkVersion < Build.VERSION_CODES.HONEYCOMB) {
            return android.R.style.Theme_InputMethod;
        }
        if (targetSdkVersion < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            return android.R.style.Theme_Holo_InputMethod;
        }
        if (targetSdkVersion < Build.VERSION_CODES.N) {
            return android.R.style.Theme_DeviceDefault_InputMethod;
        }
        return android.R.style.Theme_DeviceDefault_InputMethod;
    }

    private KeyboardDialogDelegate getKeyboardDialogDelegate() {
        if (null == mKeyboardDialogDelegate) {
            mKeyboardDialogDelegate = KeyboardDialogDelegate.create(this);
        }
        return mKeyboardDialogDelegate;
    }


}
