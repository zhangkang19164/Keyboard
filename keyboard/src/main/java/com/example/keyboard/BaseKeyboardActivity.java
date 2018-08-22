package com.example.keyboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created on 2018/8/20
 * Title:
 * Description:
 *
 * @author Android-张康
 * update 2018/8/20
 */
public class BaseKeyboardActivity extends AppCompatActivity {

    private OnActivityCallback mOnActivityCallback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mOnActivityCallback) {
            mOnActivityCallback.onDestroy();
        }
    }

    @Override
    public void onBackPressed() {
        if (null != mOnActivityCallback && mOnActivityCallback.onActivityBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

    public void setOnActivityCallback(OnActivityCallback onActivityCallback) {
        mOnActivityCallback = onActivityCallback;
    }

    public interface OnActivityCallback {
        /**
         * 点击返回事件时
         *
         * @return 是否消耗掉back点击事件
         */
        boolean onActivityBackPressed();

        /**
         * Activity销毁时调用
         */
        void onDestroy();
    }
}
