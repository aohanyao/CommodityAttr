package com.aohanyao.commodityattr.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import aohanyao.com.commodityattr.R;


/**
 * <p>作者：江俊超 on 2016/6/25 10:18</p>
 * <p>邮箱：aohanyao@gmail.com</p>
 * <p>图片加载帮助类</p>
 */
public class ImageHelper {
    private static String TAG = "ImageHelper";

    /**
     * Glide 加载图片
     *
     * @param mContext
     * @param imageView
     */
    public static void loadImageFromGlide(Context mContext, String url, ImageView imageView) {
        Glide.with(mContext).load(url)
                .error(R.mipmap.image_loading)
                .placeholder(R.mipmap.image_loading)
                .into(imageView);
    }

    public static void clearMoneryCache(Context mContext) {
        if (mContext != null) {
            L.e(TAG, "清理图片缓存");
            Glide.get(mContext).clearMemory();
            System.gc();
        }
    }

}
