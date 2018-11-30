package com.example.keyboard.dialog;

import android.content.Context;
import android.content.ContextWrapper;
import android.widget.EditText;

/**
 * Created on 2018/8/22
 * Title:
 * Description:
 *
 * @author Android-张康
 * update 2018/8/22
 */
public class KeyboardDialogFactory {
    private static KeyboardDialog sKeyboardDialog;

    public static KeyboardDialog create(EditText editText) {
        if (null == sKeyboardDialog) {
            sKeyboardDialog = create(editText.getContext());
        } else {
            Context context = sKeyboardDialog.getContext();
            if (context != editText.getContext()) {
                if (context instanceof ContextWrapper) {
                    context = ((ContextWrapper) context).getBaseContext();
                }
                if (context != editText.getContext()) {
                    sKeyboardDialog.dismiss();
                    sKeyboardDialog = create(editText.getContext());
                }
            }
        }
        sKeyboardDialog.focusIn(editText);
        return sKeyboardDialog;
    }

    static void onDestroy() {
        sKeyboardDialog = null;
    }

    private static KeyboardDialog create(Context context) {
        KeyboardDialog keyboardDialog = new KeyboardDialog(context);
        return keyboardDialog;
    }

}
