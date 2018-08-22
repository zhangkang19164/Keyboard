package com.example.keyboard.keybaord;

import android.support.annotation.StringDef;

/**
 * Created on 2018/8/21
 * Title: 键盘的类型
 * Description:
 *
 * @author Android-张康
 * update 2018/8/21
 */
@StringDef({KeyboardKeys.KEYBOARD_NUMBER, KeyboardKeys.KEYBOARD_NUMBER_ALL,
        KeyboardKeys.KEYBOARD_NUMBER_DECIMAL, KeyboardKeys.KEYBOARD_ENGLISH_LOWERCASE,
        KeyboardKeys.KEYBOARD_ENGLISH_CAPITALIZED})
public @interface KeyboardType {
}
