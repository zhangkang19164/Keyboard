package com.example.keyboard;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created on 2018/8/16
 * Title:
 * Description:
 *
 * @author Android-张康
 * update 2018/8/16
 */
public class Tools {
    /**
     * 隐藏键盘
     *
     * @param editText 需要操作的输入框
     */
    public static void hideSoftInputFromWindow(EditText editText) {
        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && imm.isActive(editText)) {
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }

    /**
     * 显示键盘
     *
     * @param editText 需要操作的输入框
     */
    public static void showSoftInput(EditText editText) {
        if (!editText.isEnabled()) {
            return;
        }
        if (!editText.hasFocus()) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(editText, 0);
        }
    }

}
