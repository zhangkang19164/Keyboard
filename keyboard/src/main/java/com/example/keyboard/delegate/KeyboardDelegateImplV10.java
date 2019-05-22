package com.example.keyboard.delegate;

import android.util.AttributeSet;
import android.widget.EditText;

import com.example.keyboard.KeyboardEditText;

/**
 * Created on 2018/8/17
 * Title: 系统版本大于等于10时会使用该类
 * Description:
 *
 * @author Android-张康
 * update 2018/8/17
 */
public class KeyboardDelegateImplV10 extends KeyboardDelegate {


    public KeyboardDelegateImplV10(KeyboardEditText editText, AttributeSet attrs) {
        super(editText, attrs);
    }
}
