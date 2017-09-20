package com.aohanyao.lib2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.aohanyao.commodity.library.CommoditySpiderHelper;
import com.aohanyao.commodityattr.mvp.contract.CommodityContract;
import com.aohanyao.commodityattr.mvp.presenter.CommodityPresenter;

import java.util.Map;
import java.util.TreeMap;

import aohanyao.com.commodityattr.R;

public class MainActivity extends AppCompatActivity implements CommodityContract.View {

    private CommoditySpiderHelper mCommoditySpiderHelper;
    private CommodityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }



    public void select(View view) {
//        presenter.showDialog();
        //创建筛选参数
        Map<String, String> param = new TreeMap<>();
        param.put("颜色", "流光金");
//        param.put("制式", "全网通版");
//        param.put("国家", "大陆");
//        param.put("内存", "3GB+32GB");
        //mCommoditySpiderHelper.filter(param);
        if (presenter == null) {
            presenter = new CommodityPresenter(this,this);
            presenter.initData();
        }
          presenter.showDialog();
        //可以筛选出剩余的属性
//        presenter.filterAttr(param);

    }


    @Override
    protected void onStart() {
        super.onStart();
    }

}
