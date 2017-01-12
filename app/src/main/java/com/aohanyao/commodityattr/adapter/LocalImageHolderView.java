package com.aohanyao.commodityattr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.aohanyao.commodityattr.util.ImageHelper;
import com.bigkoo.convenientbanner.holder.Holder;

import aohanyao.com.commodityattr.R;

/**
 * 商品详情的轮播图
 */
public class LocalImageHolderView implements Holder<String> {
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_top_news_item, null);
        imageView = (ImageView) view.findViewById(R.id.iv_top_news);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return view;
    }

    @Override
    public void UpdateUI(Context context, final int position, String url) {
        ImageHelper.loadImageFromGlide(context, url, imageView);
       // L.e("com.sjqianjin.dyshop.customer.main.purchase.goods.domain.LocalImageHolderView",url);
    }
}