package com.justsafe.libarch.utils;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewParent;

/**
 * ELabMcAndroid
 * <p>
 * Created by 李阳 on 2020/9/1 0001
 * Copyright © 2020年 新广科技. All rights reserved.
 * <p>
 * Describe:
 */
public class UiUtils {

    /**
     * 是否重叠
     *
     * @param x1
     * @param y1
     * @param width1
     * @param height1
     * @param x2
     * @param y2
     * @param width2
     * @param height2
     * @return
     */
    public static boolean isOverLapping(float x1, float y1, int width1,
                                        int height1, float x2, float y2, int width2, int height2) {
        if (x1 <= x2 && x1 + width1 <= x2) {
            return false;
        } else if (x2 <= x1 && x2 + width2 <= x1) {
            return false;
        } else if (y1 <= y2 && y1 + height1 <= y2) {
            return false;
        } else if (y2 <= y1 && y2 + height2 <= y1) {
            return false;
        }
        return true;
    }

    public static boolean isOverLapping(View v1, View v2) {
        float x1 = v1.getX();
        float y1 = v1.getY();
        int width1 = v1.getWidth();
        int height1 = v1.getHeight();

        float x2 = v2.getX();
        float y2 = v2.getY();
        int width2 = v2.getWidth();
        int height2 = v2.getHeight();
        return isOverLapping(x1, y1, width1, height1, x2, y2, width2, height2);

    }


    /**
     * Get the boundary of a view in screen coordinates.
     * 类似 Windows SDK 中的 GetWindowRect + ClientToScreen
     * @param v
     */
    public static Rect getRectInScreen(View v) {
        final int w = v.getWidth();
        final int h = v.getHeight();
        Rect r=new Rect();
        r.left = v.getLeft();
        r.top = v.getTop();
        r.right = r.left + w;
        r.bottom = r.top + h;

        ViewParent p = v.getParent();
        while(p instanceof View) {
            v = (View)p;
            p = v.getParent();

            r.left += v.getLeft();
            r.top += v.getTop();
            r.right = r.left + w;
            r.bottom = r.top + h;
        }

        return r;
    }
}
