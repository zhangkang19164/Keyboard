package com.example.keyboard.delegate;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.EditText;

import com.example.keyboard.KeyboardEditText;

/**
 * Created on 2018/8/17
 * Title:系统版本大于等于21时会使用该类
 * Description:
 *
 * @author Android-张康
 * update 2018/8/17
 */
public class KeyboardDelegateImplV21 extends KeyboardDelegate {


    KeyboardDelegateImplV21(KeyboardEditText editText, AttributeSet attrs) {
        super(editText, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void showSystemSoftInput(boolean isShow) {
        mEditText.setShowSoftInputOnFocus(isShow);
    }
}
