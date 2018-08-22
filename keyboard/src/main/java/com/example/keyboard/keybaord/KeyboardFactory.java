package com.example.keyboard.keybaord;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.text.InputType;

import com.example.keyboard.R;

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
    private static Keyboard sNumberKeyboard;
    /**
     * 数字全键盘
     */
    private static Keyboard sNumberAllKeyboard;
    /**
     * 带小数点的键盘
     */
    private static Keyboard sNumberDecimalKeyboard;
    /**
     * 小写英文键盘
     */
    private static Keyboard sLowercaseEnglishKeyboard;
    /**
     * 大写英文键盘
     */
    private static Keyboard sCapitalizedEnglishKeyboard;

    public static String getKeyboardType(int inputType) {
        String keyboardType;
        switch (inputType) {
            case InputType.TYPE_CLASS_NUMBER:
            case InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED:
            case InputType.TYPE_CLASS_PHONE:
                keyboardType = KeyboardKeys.KEYBOARD_NUMBER;
                break;
            case InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL:
                keyboardType = KeyboardKeys.KEYBOARD_NUMBER_DECIMAL;
                break;
            case InputType.TYPE_CLASS_TEXT:
                keyboardType = KeyboardKeys.KEYBOARD_ENGLISH_LOWERCASE;
                break;
            default:
                keyboardType = KeyboardKeys.KEYBOARD_ENGLISH_LOWERCASE;
                break;
        }
        return keyboardType;
    }

    public static Keyboard getKeyboard(Context context, int inputType) {
        return getKeyboard(context, getKeyboardType(inputType));
    }

    public static Keyboard getKeyboard(Context context, @KeyboardType String keyboardType) {
        switch (keyboardType) {
            case KeyboardKeys.KEYBOARD_NUMBER:
                if (null == sNumberKeyboard) {
                    sNumberKeyboard = new Keyboard(context, R.xml.keyboard_number);
                }
                return sNumberKeyboard;
            case KeyboardKeys.KEYBOARD_NUMBER_ALL:
                if (null == sNumberAllKeyboard) {
                    sNumberAllKeyboard = new Keyboard(context, R.xml.keyboard_number_all);
                }
                return sNumberAllKeyboard;
            case KeyboardKeys.KEYBOARD_NUMBER_DECIMAL:
                if (null == sNumberDecimalKeyboard) {
                    sNumberDecimalKeyboard = new Keyboard(context, R.xml.keyboard_number_decimal);
                }
                return sNumberDecimalKeyboard;
            case KeyboardKeys.KEYBOARD_ENGLISH_CAPITALIZED:
                if (null == sCapitalizedEnglishKeyboard) {
                    sCapitalizedEnglishKeyboard = new Keyboard(context, R.xml.keyboard_english_capitalized);
                }
                return sCapitalizedEnglishKeyboard;
            case KeyboardKeys.KEYBOARD_ENGLISH_LOWERCASE:
            default:
                if (null == sLowercaseEnglishKeyboard) {
                    sLowercaseEnglishKeyboard = new Keyboard(context, R.xml.keyboard_english_lowercase);
                }
                return sLowercaseEnglishKeyboard;
        }
    }

}
