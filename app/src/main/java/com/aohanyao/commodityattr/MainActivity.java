package com.aohanyao.commodityattr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;

import aohanyao.com.commodityattr.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity  {
    @Bind(R.id.iv_shop_photo)
    ConvenientBanner convenientBanner;
    @Bind(R.id.tv_shop_price)
    TextView tvShopPrice;
    @Bind(R.id.tv_shop_name)
    TextView tvShopName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    public void select(View view) {
    }

    /*@Override
    public void changeData(List<String> imgUrls, String price) {
        tvShopPrice.setText(price);
        convenientBanner.setCanLoop(true);
        convenientBanner.setPages(
                () -> new LocalImageHolderView(), imgUrls)
                .setPageIndicator(new int[]{R.drawable.page_indicator_f, R.drawable.page_indicator_n})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
    }*/


    @Override
    protected void onStart() {
        super.onStart();
        convenientBanner.startTurning(3000);
    }


}
