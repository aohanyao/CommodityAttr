package com.jjc.commodity.attr.library.util;

import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by 江俊超 on 2016/12/22 0022.
 * <p>Gihub https://github.com/aohanyao</p>
 * <p>自定义VIEW测量工具类</p>
 */

public class MeasureSpecUtisl {
    /**
     * 测量
     * @param measureSpec
     * @return
     */
    public static int measureSpecSize(@NonNull int measureSpec, @NonNull int defaultSize) {
        int result = 0;
        int mode = View.MeasureSpec.getMode(measureSpec);
        int size = View.MeasureSpec.getSize(measureSpec);

        if (mode == View.MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = defaultSize;
            if (mode == View.MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }
}
