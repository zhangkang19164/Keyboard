package com.example.keyboard.keybaord;

/**
 * Created on 2018/8/21
 * Title:
 * Description:
 *
 * @author Android-张康
 * update 2018/8/21
 */
public class KeyboardKeys {
    /**
     * 数字键盘类型
     */
    public static final String KEYBOARD_NUMBER = "numberKeyboard";
    /**
     * 数字全键盘
     */
    public static final String KEYBOARD_NUMBER_ALL = "numberAllKeyboard";
    /**
     * 带小数点的数字键盘
     */
    public static final String KEYBOARD_NUMBER_DECIMAL = "numberDecimalKeyboard";
    /**
     * 小写英文键盘
     */
    public static final String KEYBOARD_ENGLISH_LOWERCASE = "LowercaseEnglishKeyboard";
    /**
     * 大写英文键盘
     */
    public static final String KEYBOARD_ENGLISH_CAPITALIZED = "CapitalizedEnglishKeyboard";
    /**
     * 可以切换到系统键盘的键盘
     */
    public static final String KEYBOARD_SWITCH_TO_SYSTEM = "switchToSystemKeyboard";

    /**
     * 清除
     */
    public static final int KEYCODE_CLEAR = -7;
    /**
     * 隐藏
     */
    public static final int KEYCODE_HIDE = -8;
    /**
     * 切换到小写键盘
     */
    public static final int KEYCODE_SWITCH_TO_ENGLISH_LOWERCASE = -9;
    /**
     * 切换到大写键盘
     */
    public static final int KEYCODE_SWITCH_TO_ENGLISH_CAPITALIZED = -10;
    /**
     * 切换到数字键盘
     */
    public static final int KEYCODE_SWITCH_TO_NUMBER = -11;
    /**
     * 切换到英文键盘
     */
    public static final int KEYCODE_SWITCH_TO_ENGLISH = -12;
    /**
     * 切换到系统键盘
     */
    public static final int KEYCODE_SWITCH_TO_SYSTEM = -13;

    /**
     * 键盘类型
     */
    public static final int KEYBOARD_TYPE_SWITCH_TO_SYSTEM = 0x00100000;
}
