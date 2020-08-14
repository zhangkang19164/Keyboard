package com.example.keyboard.keybaord;

import android.content.Context;
import android.text.InputType;

import com.example.keyboard.KeyboardEditText;
import com.example.keyboard.R;

import self.zhangkang.android.copykeyboard.CopyKeyboard;

/**
 * Created on 2018/8/21
 * Title:
 * Description:
 *
 * @author Android-张康
 * update 2018/8/21
 */
public class KeyboardFactory {
    /**
     * 数字键盘
     */
    private static CopyKeyboard sNumberKeyboard;
    /**
     * 数字全键盘
     */
    private static CopyKeyboard sNumberAllKeyboard;
    /**
     * 带小数点的键盘
     */
    private static CopyKeyboard sNumberDecimalKeyboard;
    /**
     * 小写英文键盘
     */
    private static CopyKeyboard sLowercaseEnglishKeyboard;
    /**
     * 大写英文键盘
     */
    private static CopyKeyboard sCapitalizedEnglishKeyboard;

    private static CopyKeyboard sSwitchToSystemKeyboard;

    public static String getKeyboardType(KeyboardEditText editText) {
        int keyboardType = editText.getKeyboardType();
        if (keyboardType == KeyboardKeys.KEYBOARD_TYPE_SWITCH_TO_SYSTEM) {
            return KeyboardKeys.KEYBOARD_SWITCH_TO_SYSTEM;
        }
        switch (editText.getInputType()) {
            case InputType.TYPE_CLASS_NUMBER:
            case InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED:
            case InputType.TYPE_CLASS_PHONE:
                return KeyboardKeys.KEYBOARD_NUMBER;
            case InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL:
                return KeyboardKeys.KEYBOARD_NUMBER_DECIMAL;
            case InputType.TYPE_CLASS_TEXT:
                return KeyboardKeys.KEYBOARD_ENGLISH_LOWERCASE;
            default:
                return KeyboardKeys.KEYBOARD_ENGLISH_LOWERCASE;
        }
    }

    public static CopyKeyboard getKeyboard(KeyboardEditText editText) {
        return getKeyboard(editText.getContext(), getKeyboardType(editText));
    }

    public static CopyKeyboard getKeyboard(Context context, @KeyboardType String keyboardType) {
        switch (keyboardType) {
            case KeyboardKeys.KEYBOARD_NUMBER:
                if (null == sNumberKeyboard) {
                    sNumberKeyboard = new CopyKeyboard(context, R.xml.keyboard_number);
                }
                return sNumberKeyboard;
            case KeyboardKeys.KEYBOARD_NUMBER_ALL:
                if (null == sNumberAllKeyboard) {
                    sNumberAllKeyboard = new CopyKeyboard(context, R.xml.keyboard_number_all);
                }
                return sNumberAllKeyboard;
            case KeyboardKeys.KEYBOARD_NUMBER_DECIMAL:
                if (null == sNumberDecimalKeyboard) {
                    sNumberDecimalKeyboard = new CopyKeyboard(context, R.xml.keyboard_number_decimal);
                }
                return sNumberDecimalKeyboard;
            case KeyboardKeys.KEYBOARD_ENGLISH_CAPITALIZED:
                if (null == sCapitalizedEnglishKeyboard) {
                    sCapitalizedEnglishKeyboard = new CopyKeyboard(context, R.xml.keyboard_english_capitalized);
                }
                return sCapitalizedEnglishKeyboard;
            case KeyboardKeys.KEYBOARD_SWITCH_TO_SYSTEM:
                if (null == sSwitchToSystemKeyboard) {
                    sSwitchToSystemKeyboard = new CopyKeyboard(context, R.xml.keyboard_number_switch_to_system);
                }
                return sSwitchToSystemKeyboard;
            case KeyboardKeys.KEYBOARD_ENGLISH_LOWERCASE:
            default:
                if (null == sLowercaseEnglishKeyboard) {
                    sLowercaseEnglishKeyboard = new CopyKeyboard(context, R.xml.keyboard_english_lowercase);
                }
                return sLowercaseEnglishKeyboard;
        }
    }

}
