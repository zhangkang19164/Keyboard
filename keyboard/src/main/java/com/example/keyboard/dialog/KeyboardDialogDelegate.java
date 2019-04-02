package com.example.keyboard.dialog;

import android.content.Context;
import android.graphics.Rect;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import com.example.keyboard.R;
import com.example.keyboard.keybaord.KeyboardFactory;
import com.example.keyboard.keybaord.KeyboardKeys;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created on 2018/8/22
 * Title:
 * Description:
 *
 * @author Android-张康
 * update 2018/8/22
 */
public class KeyboardDialogDelegate implements KeyboardView.OnKeyboardActionListener {
    private final Rect mBounds = new Rect();
    private LayoutInflater mInflater;
    private KeyboardDialog mKeyboardDialog;
    private KeyboardView mNumberKeyboardView;
    private KeyboardView mEnglishKeyboardView;
    private String mLastEnglishKeyboardType;
    private String mLastKeyboardType;
    private EditText mEditText;

    public static KeyboardDialogDelegate create(KeyboardDialog keyboardDialog) {
        return new KeyboardDialogDelegate(keyboardDialog);
    }

    private KeyboardDialogDelegate(KeyboardDialog keyboardDialog) {
        mKeyboardDialog = keyboardDialog;
    }


    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        if (null == mEditText) {
            return;
        }
        int selectionStart = mEditText.getSelectionStart();
        int selectionEnd = mEditText.getSelectionEnd();
        if (selectionStart == selectionEnd) {
            selectionStart = selectionEnd - 1;
            if (selectionStart < 0) {
                selectionStart = 0;
            }
        }
        switch (primaryCode) {
            case Keyboard.KEYCODE_DONE:
                mKeyboardDialog.dismiss();
                break;
            case Keyboard.KEYCODE_DELETE:
                mEditText.getEditableText().delete(selectionStart, selectionEnd);
                break;
            case KeyboardKeys.KEYCODE_CLEAR:
                mEditText.getText().clear();
                break;
            case KeyboardKeys.KEYCODE_HIDE:
                mKeyboardDialog.dismiss();
                break;
            case KeyboardKeys.KEYCODE_SWITCH_TO_ENGLISH_LOWERCASE:
                mLastEnglishKeyboardType = KeyboardKeys.KEYBOARD_ENGLISH_LOWERCASE;
                setKeyboard(KeyboardKeys.KEYBOARD_ENGLISH_LOWERCASE);
                break;
            case KeyboardKeys.KEYCODE_SWITCH_TO_ENGLISH_CAPITALIZED:
                mLastEnglishKeyboardType = KeyboardKeys.KEYBOARD_ENGLISH_CAPITALIZED;
                setKeyboard(KeyboardKeys.KEYBOARD_ENGLISH_CAPITALIZED);
                break;
            case KeyboardKeys.KEYCODE_SWITCH_TO_NUMBER:
                setKeyboard(KeyboardKeys.KEYBOARD_NUMBER_ALL);
                break;
            case KeyboardKeys.KEYCODE_SWITCH_TO_ENGLISH:
                String keyboardType;
                if (TextUtils.isEmpty(mLastEnglishKeyboardType)) {
                    keyboardType = KeyboardKeys.KEYBOARD_ENGLISH_LOWERCASE;
                } else {
                    keyboardType = mLastEnglishKeyboardType;
                }
                setKeyboard(keyboardType);
                break;
            default:
                if (isNumber(primaryCode)) {
                    mEditText.getEditableText().insert(selectionEnd, String.valueOf((char) primaryCode));
                } else if (isEnglishAlphabet(primaryCode)) {
                    mEditText.getEditableText().insert(selectionEnd, String.valueOf((char) primaryCode));
                }
                break;
        }
    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {
        mKeyboardDialog.dismiss();
    }

    @Override
    public void swipeUp() {

    }

    public void onCreate(Bundle savedInstanceState) {
        initView();
        getWindow().setLayout(MATCH_PARENT, WRAP_CONTENT);
    }

    public boolean dispatchTouchEvent(@NonNull MotionEvent ev) {
        getWindow().getDecorView().getHitRect(mBounds);
        return false;
    }

    public void focusIn(@NonNull EditText editText) {
        if (null != mEditText && mEditText == editText) {
            return;
        }
        mEditText = editText;
        setKeyboard(KeyboardFactory.getKeyboardType(mEditText.getInputType()));
    }

    public void focusOut(@NonNull EditText editText) {
        if (mEditText == editText) {
            mEditText = null;
        }

    }

    private void initView() {
        if (null != mEditText) {
            focusIn(mEditText);
        }
    }


    private void setKeyboard(String keyboardType) {
        if (!TextUtils.isEmpty(mLastKeyboardType) && mLastKeyboardType.equals(keyboardType)) {
            return;
        }
        mLastKeyboardType = keyboardType;
        if (isNumberKeyboard(keyboardType)) {
            KeyboardView keyboardView = mKeyboardDialog.findViewById(R.id.keyboard_number);
            if (null == keyboardView) {
                if (null == mNumberKeyboardView) {
                    mNumberKeyboardView = (KeyboardView) getInflater().inflate(R.layout.dialog_keyboard_number, null);
                    mNumberKeyboardView.setOnKeyboardActionListener(this);
                }
                keyboardView = mNumberKeyboardView;
                mKeyboardDialog.addContentView(keyboardView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            View viewById = mKeyboardDialog.findViewById(R.id.keyboard_english);
            if (null != viewById && viewById.getVisibility() != View.GONE) {
                viewById.setVisibility(View.GONE);
            }
            if (keyboardView.getVisibility() != View.VISIBLE) {
                keyboardView.setVisibility(View.VISIBLE);
            }
            String tag = (String) keyboardView.getTag();
            if (keyboardType.equals(tag)) {
                return;
            }
            keyboardView.setTag(keyboardType);
            keyboardView.setKeyboard(KeyboardFactory.getKeyboard(keyboardView.getContext(), keyboardType));
        } else {
            KeyboardView keyboardView = mKeyboardDialog.findViewById(R.id.keyboard_english);
            if (null == keyboardView) {
                if (null == mEnglishKeyboardView) {
                    mEnglishKeyboardView = (KeyboardView) getInflater().inflate(R.layout.dialog_keyboard_english, null);
                    mEnglishKeyboardView.setOnKeyboardActionListener(this);
                }
                keyboardView = mEnglishKeyboardView;
                mKeyboardDialog.addContentView(keyboardView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            View viewById = mKeyboardDialog.findViewById(R.id.keyboard_number);
            if (null != viewById && viewById.getVisibility() != View.GONE) {
                viewById.setVisibility(View.GONE);
            }
            if (keyboardView.getVisibility() != View.VISIBLE) {
                keyboardView.setVisibility(View.VISIBLE);
            }
            String tag = (String) keyboardView.getTag();
            if (keyboardType.equals(tag)) {
                return;
            }
            keyboardView.setTag(keyboardType);
            keyboardView.setKeyboard(KeyboardFactory.getKeyboard(keyboardView.getContext(), keyboardType));
        }
    }

    @Nullable
    private Window getWindow() {
        return mKeyboardDialog.getWindow();
    }

    private Context getContext() {
        return mKeyboardDialog.getContext();
    }


    private boolean isNumberKeyboard(String keyboardType) {
        return KeyboardKeys.KEYBOARD_NUMBER.equals(keyboardType) ||
                KeyboardKeys.KEYBOARD_NUMBER_ALL.equals(keyboardType) ||
                KeyboardKeys.KEYBOARD_NUMBER_DECIMAL.equals(keyboardType);
    }

    private boolean isEnglishKeyboard(String keyboardType) {
        return KeyboardKeys.KEYBOARD_ENGLISH_LOWERCASE.equals(keyboardType) ||
                KeyboardKeys.KEYBOARD_ENGLISH_CAPITALIZED.equals(keyboardType);
    }

    /**
     * 判断是不是数字操作，包含小数点
     *
     * @param primaryCode 按键输出的unicode值
     * @return 是返回true, 不是返回 false
     */
    private boolean isNumber(int primaryCode) {
        //46 是小数点，48-57代表0-9
        return primaryCode == 46 || primaryCode > 47 && primaryCode < 58;
    }

    /**
     * 判断是不是字母操作，包含空格
     *
     * @param primaryCode 按键输出的unicode值
     * @return 是返回true, 不是返回 false
     */
    private boolean isEnglishAlphabet(int primaryCode) {
        //32代表空格，65-90代表A-Z，97-122代表a-z
        return primaryCode == 32 || primaryCode >= 65 && primaryCode <= 90 || primaryCode >= 97 && primaryCode <= 122;
    }

    private LayoutInflater getInflater() {
        if (null == mInflater) {
            mInflater = LayoutInflater.from(mKeyboardDialog.getContext());
        }
        return mInflater;
    }
}
