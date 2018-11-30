package com.example.keyboard.delegate;

import android.os.Build;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.EditText;

import com.example.keyboard.dialog.KeyboardDialog;
import com.example.keyboard.dialog.KeyboardDialogFactory;

import java.lang.reflect.Method;

/**
 * Created on 2018/8/17
 * Title:
 * Description:
 *
 * @author Android-张康
 * update 2018/8/17
 */
public class KeyboardDelegate {
    private boolean mShowSystemSoftInput;
    protected EditText mEditText;
    protected KeyboardDialog mKeyboardDialog;

    public static KeyboardDelegate create(EditText editText, AttributeSet attrs) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return new KeyboardDelegateImplV21(editText, attrs);
        }
        return new KeyboardDelegate(editText, attrs);
    }

    public KeyboardDelegate(EditText editText, AttributeSet attrs) {
        mEditText = editText;
        showSystemSoftInput(false);
    }

    /**
     * 隐藏系统键盘，不同版本的处理不一样
     */
    public void showSystemSoftInput(boolean isShow) {
        if (isShow && isShowing()) {
            hideKeyboard();
        }
        mShowSystemSoftInput = isShow;
        try {
            Class<? extends EditText> cls = mEditText.getClass();
            Method setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
            setShowSoftInputOnFocus.setAccessible(true);
            setShowSoftInputOnFocus.invoke(this, isShow);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setInputType(int type) {
        showKeyboard();
    }


    public boolean onTouchEvent(MotionEvent event) {
        boolean isShowKeyboard = event.getAction() == MotionEvent.ACTION_UP && mEditText.isFocused();
        isShowKeyboard = isShowKeyboard && (mEditText.isTextSelectable() || mEditText.onCheckIsTextEditor() && mEditText.isEnabled());
        if (isShowKeyboard) {
            showKeyboard();
        }
        return false;
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //如果点击的是返回键，且当前键盘是显示状态，关闭键盘并消耗改点击事件
        if (keyCode == KeyEvent.KEYCODE_BACK && isShowing()) {
            hideKeyboard();
            return true;
        }
        return false;
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_DPAD_CENTER == keyCode) {
            showKeyboard();
        }
        return false;
    }

    public void onFocusChanged(boolean focused) {
        if (focused) {
            if (null != mKeyboardDialog) {
                mKeyboardDialog.focusIn(mEditText);
            }
        } else {
            if (null != mKeyboardDialog) {
                mKeyboardDialog.focusOut(mEditText);
            }
        }
    }

    public void setEnabled(boolean enabled) {
        if (!enabled) {
            hideKeyboard();
        }
    }

    public void onDetachedFromWindow() {
        if (null != mKeyboardDialog) {
            mKeyboardDialog.focusOut(mEditText);
        }
    }


    public void showKeyboard() {
        if (mShowSystemSoftInput) {
            hideKeyboard();
            return;
        }
        if (null == mKeyboardDialog) {
            mKeyboardDialog = KeyboardDialogFactory.create(mEditText);
        }
        mKeyboardDialog.focusIn(mEditText);
        mKeyboardDialog.show();
    }

    public void hideKeyboard() {
        if (null != mKeyboardDialog && mKeyboardDialog.isShowing()) {
            mKeyboardDialog.dismiss();
        }
    }

    public boolean isShowing() {
        return null != mKeyboardDialog && mKeyboardDialog.isShowing();
    }


}
