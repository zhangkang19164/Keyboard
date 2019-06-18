package com.example.keyboard.keybaord;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Canvas;
import android.inputmethodservice.Keyboard;

/**
 * Created on 2019/6/18
 * Title: 多背景键盘
 * Description: 通过自定义 Keyboard 来完成不同的键盘使用不同的背景
 *
 * @author Android-张康
 */
public class MultiBackgroundKeyboard extends Keyboard {
    MultiBackgroundKeyboard(Context context, int xmlLayoutResId) {
        super(context, xmlLayoutResId, 0);
    }

    @Override
    protected Key createKeyFromXml(Resources res, Row parent, int x, int y, XmlResourceParser parser) {
        //拦截父类创建Key的操作，返回自己的Key
        return new MultiBackgroundKey(res, parent, x, y, parser);
    }

    /**
     * {@link android.inputmethodservice.KeyboardView#onDraw(Canvas)}
     * 使用不同的状态来实现按钮使用不同的背景颜色
     */
    private static class MultiBackgroundKey extends Key {

        private final static int[] KEY_STATE_NORMAL_ON = {
                android.R.attr.state_checkable,
                android.R.attr.state_checked
        };

        private final static int[] KEY_STATE_PRESSED_ON = {
                android.R.attr.state_pressed,
                android.R.attr.state_checkable,
                android.R.attr.state_checked
        };

        private final static int[] KEY_STATE_NORMAL_OFF = {
                android.R.attr.state_checkable,
                android.R.attr.state_selected
        };

        private final static int[] KEY_STATE_PRESSED_OFF = {
                android.R.attr.state_pressed,
                android.R.attr.state_checkable
        };

        private final static int[] KEY_STATE_NORMAL = {
                android.R.attr.state_selected
        };

        private final static int[] KEY_STATE_PRESSED = {

        };

        private final static int[] KEY_STATE_NORMAL_ON2 = {
                android.R.attr.state_checkable,
                android.R.attr.state_checked
        };

        private final static int[] KEY_STATE_PRESSED_ON2 = {
                android.R.attr.state_pressed,
                android.R.attr.state_checkable,
                android.R.attr.state_checked
        };

        private final static int[] KEY_STATE_NORMAL_OFF2 = {
                android.R.attr.state_checkable,
                android.R.attr.state_activated
        };

        private final static int[] KEY_STATE_PRESSED_OFF2 = {
                android.R.attr.state_pressed,
                android.R.attr.state_checkable
        };

        private final static int[] KEY_STATE_NORMAL2 = {
                android.R.attr.state_activated
        };

        private final static int[] KEY_STATE_PRESSED2 = {

        };

        private MultiBackgroundKey(Resources res, Row parent, int x, int y, XmlResourceParser parser) {
            super(res, parent, x, y, parser);
        }

        @Override
        public int[] getCurrentDrawableState() {
            //如果需要修改颜色，使用返回自定义的颜色状态
            if (isNeedChange()) {
                int[] states = KEY_STATE_NORMAL;
                //on 用来控制shift建是否开启的表示，没有用到
                if (on) {
                    if (pressed) {
                        states = KEY_STATE_PRESSED_ON;
                    } else {
                        states = KEY_STATE_NORMAL_ON;
                    }
                } else {
                    if (sticky) {
                        if (pressed) {
                            states = KEY_STATE_PRESSED_OFF;
                        } else {
                            states = KEY_STATE_NORMAL_OFF;
                        }
                    } else {
                        if (pressed) {
                            states = KEY_STATE_PRESSED;
                        }
                    }
                }
                return states;
            }
            //如果需要修改颜色，使用返回自定义的颜色状态
            if (isNeedChange2()) {
                int[] states = KEY_STATE_NORMAL2;

                if (on) {
                    if (pressed) {
                        states = KEY_STATE_PRESSED_ON2;
                    } else {
                        states = KEY_STATE_NORMAL_ON2;
                    }
                } else {
                    if (sticky) {
                        if (pressed) {
                            states = KEY_STATE_PRESSED_OFF2;
                        } else {
                            states = KEY_STATE_NORMAL_OFF2;
                        }
                    } else {
                        if (pressed) {
                            states = KEY_STATE_PRESSED2;
                        }
                    }
                }
                return states;
            }
            //返回默认的状态
            return super.getCurrentDrawableState();
        }

        private boolean isNeedChange() {
            if (null != codes) {
                for (int code : codes) {
                    if (code == Keyboard.KEYCODE_DONE) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean isNeedChange2() {
            if (null != codes) {
                for (int code : codes) {
                    if (code == Keyboard.KEYCODE_DELETE ||
                            code == KeyboardKeys.KEYCODE_CLEAR ||
                            code == KeyboardKeys.KEYCODE_HIDE) {
                        return true;
                    }
                }
            }
            return false;
        }
    }


}
