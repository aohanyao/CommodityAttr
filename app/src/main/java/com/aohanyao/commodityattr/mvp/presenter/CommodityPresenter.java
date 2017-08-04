package com.aohanyao.commodityattr.mvp.presenter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aohanyao.commodity.library.CommoditySpiderHelper;
import com.aohanyao.commodity.library.inf.OnSelectCommodityListener;
import com.aohanyao.commodity.library.model.CommoditySpiderInfo;
import com.aohanyao.commodityattr.mvp.contract.CommodityContract;
import com.aohanyao.commodityattr.ui.MyDialog;
import com.aohanyao.commodityattr.util.FileUtils;
import com.aohanyao.commodityattr.util.ImageHelper;
import com.aohanyao.lib.model.ShopDeialResponseDto;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import aohanyao.com.commodityattr.R;

/**
 * Created by 江俊超 on 8/3/2017.
 * <p>版本:1.0.0</p>
 * <b>说明<b><br/>
 * <li>商品P</li>
 */
public class CommodityPresenter extends CommodityContract.Presenter<CommodityContract.View>
        implements OnSelectCommodityListener<ShopDeialResponseDto.MsgBean.CommodityBean> {
    /**
     * 弹窗
     */
    public MyDialog mBottomSheetDialog;
    private int shopNum = 1;
    /**
     * 颜色 内存 分期 数量
     */
    private TextView tvColor, tvMomey, tvNum;
    /**
     * 增加、减少
     */
    private Button btnCut, btnAdd;
    private Button goInput;
    private TextView tvVersion;
    private TextView tvPrice;
    private TextView tvShopName;
    private ImageView ivShopPhoto;
    private String mShopImageUrl = "";
    private LinearLayout llBuyStages;

    private CommoditySpiderHelper mCommoditySpiderHelper;
    private ShopDeialResponseDto resonseDto;

    public CommodityPresenter(CommodityContract.View view) {
        super(view);
    }

    @Override
    public void showDialog() {
        mBottomSheetDialog = new MyDialog((Activity) view, R.style.GoodDialog);
        //设置退出速度
        mBottomSheetDialog.outDuration(100);
        mBottomSheetDialog.inDuration(100);
        //设置铺满
        mBottomSheetDialog.heightParam(ViewGroup.LayoutParams.WRAP_CONTENT);
        //解析视图
        View contentView = LayoutInflater.from((Activity) view).inflate(R.layout.layout_by_shop, null);
        //设置视图
        mBottomSheetDialog.setContentView(contentView);
        ImageView ivClose = (ImageView) contentView.findViewById(R.id.iv_close);
        goInput = (Button) contentView.findViewById(R.id.btn_buy_input_message);
        //-------颜色、内存、是否分期 数量
        tvColor = (TextView) contentView.findViewById(R.id.tv_shop_color);
        tvMomey = (TextView) contentView.findViewById(R.id.tv_shop_momery);
        tvVersion = (TextView) contentView.findViewById(R.id.tv_shop_version);
        tvNum = (TextView) contentView.findViewById(R.id.tv_shop_num);
        tvPrice = (TextView) contentView.findViewById(R.id.tv_shop_price);
        tvShopName = (TextView) contentView.findViewById(R.id.tv_shop_name);
        ivShopPhoto = (ImageView) contentView.findViewById(R.id.iv_shop_photo);
        llBuyStages = (LinearLayout) contentView.findViewById(R.id.ll_buy_stages);
        //-------颜色、内存、是否分期 数量
        //------------------增加 减少
        btnCut = (Button) contentView.findViewById(R.id.btn_shop_cut);
        btnAdd = (Button) contentView.findViewById(R.id.btn_shop_add);
        shopNum = 1;
        //------------------增加 减少
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        goInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //------------名字 价格
        tvShopName.setText(resonseDto.getMsg().getParent().getName());
        tvPrice.setText("￥" + resonseDto.getPricemin());
        //------------名字 价格
        ImageHelper.loadImageFromGlide((Activity) view, resonseDto.getMsg().getParent().getShowimg(), ivShopPhoto);
        mBottomSheetDialog.show();
    }

    @Override
    public void initData() {
        //模拟网络 从 assets中转换数据
        String json = FileUtils.getJson("commodity2.json", (Activity) view);
        resonseDto = new Gson().fromJson(json, ShopDeialResponseDto.class);
        List<ShopDeialResponseDto.MsgBean.CommodityBean> childs = resonseDto.getMsg().getChilds();

        List<CommoditySpiderInfo> commoditySpiderInfos = new ArrayList<>();
        for (ShopDeialResponseDto.MsgBean.CommodityBean child : childs) {

            commoditySpiderInfos.add(new CommoditySpiderInfo(child, child.getAttrinfo().getAttrs()));
        }

        //筛选帮助类
        mCommoditySpiderHelper = new CommoditySpiderHelper(commoditySpiderInfos, this);
    }


    @Override
    public void sortValues(Map<String, Set<String>> sortValues) {

    }

    @Override
    public void onSelectCommodityListener(ShopDeialResponseDto.MsgBean.CommodityBean commodity) {

    }
}
