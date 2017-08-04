package com.aohanyao.lib2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aohanyao.commodity.library.CommoditySpiderHelper;
import com.aohanyao.commodity.library.inf.OnSelectCommodityListener;
import com.aohanyao.commodity.library.model.CommoditySpiderInfo;
import com.aohanyao.commodityattr.util.FileUtils;
import com.aohanyao.commodityattr.util.L;
import com.aohanyao.lib.model.ShopDeialResponseDto;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import aohanyao.com.commodityattr.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    //    CcommodityPresenter_RxJava presenter;
    @Bind(R.id.iv_shop_photo)
    ConvenientBanner convenientBanner;
    @Bind(R.id.tv_shop_price)
    TextView tvShopPrice;
    @Bind(R.id.tv_shop_name)
    TextView tvShopName;
    private CommoditySpiderHelper mCommoditySpiderHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        initData();


    }

    private void initData() {
        String json = FileUtils.getJson("commodity2.json", this);
        ShopDeialResponseDto resonseDto = new Gson().fromJson(json, ShopDeialResponseDto.class);
        List<ShopDeialResponseDto.MsgBean.CommodityBean> childs = resonseDto.getMsg().getChilds();


        List<CommoditySpiderInfo> commoditySpiderInfos = new ArrayList<>();
        for (ShopDeialResponseDto.MsgBean.CommodityBean child : childs) {

            commoditySpiderInfos.add(new CommoditySpiderInfo(child, child.getAttrinfo().getAttrs()));
        }

        mCommoditySpiderHelper = new CommoditySpiderHelper(commoditySpiderInfos, new OnSelectCommodityListener<ShopDeialResponseDto.MsgBean.CommodityBean>() {
            @Override
            public void onSelectCommodityListener(ShopDeialResponseDto.MsgBean.CommodityBean onSelectCommodity) {
                L.e(onSelectCommodity.getName());
                Toast.makeText(MainActivity.this, onSelectCommodity.getName(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void sortValues(Map<String, Set<String>> sortValues) {
                L.e(sortValues.toString());
            }
        });


    }

    public void select(View view) {
//        presenter.showDialog();
        //创建筛选参数
        Map<String, String> param = new TreeMap<>();
        param.put("颜色", "钛银灰");
        param.put("制式", "全网通版");
        param.put("国家", "大陆");
        param.put("内存", "3GB+32GB");
        mCommoditySpiderHelper.filter(param);
    }


    @Override
    protected void onStart() {
        super.onStart();
        convenientBanner.startTurning(3000);
    }

}
