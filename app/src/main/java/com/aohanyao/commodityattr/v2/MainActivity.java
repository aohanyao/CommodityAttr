package com.aohanyao.commodityattr.v2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.aohanyao.commodityattr.v2.dto.CommodityInfoAndAttrDto;
import com.aohanyao.commodityattr.v2.model.CommodityInfo;
import com.aohanyao.commodityattr.v2.util.FileUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jjc.commodity.attr.library.ui.CommodityAttrView;

import java.util.List;

import aohanyao.com.commodityattr.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 主页  使用 第二种
 */
public class MainActivity extends AppCompatActivity {
    @Bind(R.id.cav)
    CommodityAttrView cav;
    private Activity mActivity;
    private Gson mGson;
    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mActivity = this;
        mGson = new Gson();
        initData();
    }

    /**
     * 初始化数据
     * <b>从本地json文件中获取数据</b>
     */
    private void initData() {
        //因为有的耗费时间，所有开辟一个线程来模拟获取数据
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
        String json = FileUtils.getJson("commodity.json", mActivity);
        CommodityInfoAndAttrDto infoAndAttrDto = null;
        try {
            infoAndAttrDto = mGson.fromJson(json, CommodityInfoAndAttrDto.class);
            //商品列表
            List<CommodityInfo> commodityInfos = infoAndAttrDto.getMsg().getChilds();
            cav.setAttrNames(new String[]{"制式", "内存", "颜色"});
            cav.setCommoditys(commodityInfos);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
//            }
//        }).start();
    }

}
