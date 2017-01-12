package com.aohanyao.commodityattr.presenter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aohanyao.commodityattr.adapter.ProperyTagAdapter;
import com.aohanyao.commodityattr.model.ShopDeialResponseDto;
import com.aohanyao.commodityattr.model.TagInfo;
import com.aohanyao.commodityattr.presenter.inf.CcommodityPresenterInf;
import com.aohanyao.commodityattr.presenter.inf.ShopDeialPresenterCallBack;
import com.aohanyao.commodityattr.ui.FlowTagLayout;
import com.aohanyao.commodityattr.ui.MyDialog;
import com.aohanyao.commodityattr.util.FileUtils;
import com.aohanyao.commodityattr.util.ImageHelper;
import com.aohanyao.commodityattr.util.L;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import aohanyao.com.commodityattr.R;
import rx.Observable;

/**
 * <p>作者：江俊超 on 2016/9/12 10:44</p>
 * <p>邮箱：928692385@qq.com</p>
 * <p></p>
 */
public class CcommodityPresenter implements CcommodityPresenterInf, View.OnClickListener {
    private int shopNum = 1;
    /**
     * 颜色 内存 分期 数量
     */
    private TextView tvColor, tvMomey, tvNum;
    /**
     * 增加、减少
     */
    private Button btnCut, btnAdd;

    /**
     * 弹窗
     */
    public MyDialog mBottomSheetDialog;
    /**
     * 引用上下文
     */
    private Activity mActivity;
    /**
     * 弹窗布局
     */
    private View contentView;
    /**
     * 颜色列表
     */
    private FlowTagLayout rlShopColor;
    /**
     * 内存列表
     */
    private FlowTagLayout rlShopMomery;
    /**
     * 版本
     */
    private FlowTagLayout rlShopVersion;

    /**
     * 分期列表
     */
    private FlowTagLayout rlShopStages;
    private String parentid;
    private String strColor;
    private String strMemory;
    private String strVersion;
    private List<TagInfo> mColors;
    private List<String> mTempColors;
    private List<TagInfo> mMonerys;
    private List<String> mTempMonerys;
    private List<TagInfo> mStages;
    private List<String> mTempStages;
    private List<TagInfo> mVersions;
    private List<String> mTempVersions;
    private List<String> mImages;
    private TextView tvVersion;
    ShopDeialPresenterCallBack callBack;
    private List<ShopDeialResponseDto.DataEntity.ChildsEntity> shopLists;
    private TextView tvPrice;
    private TextView tvShopName;
    private ShopDeialResponseDto responseDto;
    private ImageView ivShopPhoto;
    private String mShopImageUrl = "";
    private ProperyTagAdapter colorAdapter;
    private ProperyTagAdapter momeryAdapter;
    private ShopDeialResponseDto.DataEntity.ChildsEntity selectGoods;//选中的商品
    private String strStages;
    private ProperyTagAdapter versionAdapter;
    private ProperyTagAdapter stagesAdapter;
    private int versionPositon = 0;
    private int momeryPositon = 0;
    private int colorPositon = 0;
    private int initShopStagesCount = 1;
    private LinearLayout llBuyStages;
    private Button goInput;
    private List<String> tempImageColor;

    public CcommodityPresenter(Activity mActivity, ShopDeialPresenterCallBack callBack) {
        this.mActivity = mActivity;
        this.callBack = callBack;

        String json = FileUtils.getJson("commodity.json", mActivity);
        responseDto = new Gson().fromJson(json, ShopDeialResponseDto.class);
        initGoodsInfo();
    }

    @Override
    public void showDialog() {
        strColor = null;
        strVersion = null;
        strMemory = null;
//        initShopStagesCount=1; //TODO 分期 二次预订的时候不显示，加上这句话初始化就好
        //----------------弹出窗口
        mBottomSheetDialog = new MyDialog(mActivity, R.style.GoodDialog);
        //设置退出速度
        mBottomSheetDialog.outDuration(100);
        mBottomSheetDialog.inDuration(100);
        //设置铺满
        mBottomSheetDialog.heightParam(ViewGroup.LayoutParams.WRAP_CONTENT);
        //解析视图
        contentView = LayoutInflater.from(mActivity).inflate(R.layout.layout_by_shop, null);
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
        ivClose.setOnClickListener(this);
        goInput.setOnClickListener(this);

        //--------------------------商品颜色
        rlShopColor = (FlowTagLayout) contentView.findViewById(R.id.rl_shop_color);
        initShopColor();
        //--------------------------商品颜色
        //--------------------------内存列表
        rlShopMomery = (FlowTagLayout) contentView.findViewById(R.id.rl_shop_momery);
        initShopMomery();
        //--------------------------内存列表

        //-------------------------制式版本
        rlShopVersion = (FlowTagLayout) contentView.findViewById(R.id.rl_shop_version);
        initShopVersion();
        //-------------------------制式版本
        btnCut.setOnClickListener(this);
        btnAdd.setOnClickListener(this);

        //------------名字 价格
        tvShopName.setText(responseDto.getMsg().getParent().getName());
        tvPrice.setText("￥" + responseDto.getPricemin());
        //------------名字 价格
         ImageHelper.loadImageFromGlide(mActivity, responseDto.getMsg().getParent().getShowimg(), ivShopPhoto);
        mBottomSheetDialog.show();
    }

    /**
     * 初始化颜色
     *
     * @hint
     */
    private void initShopColor() {
        for (TagInfo mColor : mColors) {
            //初始化所有的选项为未选择状态
            mColor.setSelect(false);
        }
        tvColor.setText("\"未选择颜色\"");
        mColors.get(colorPositon).setSelect(true);
        colorAdapter = new ProperyTagAdapter(mActivity, mColors);
        rlShopColor.setAdapter(colorAdapter);
        colorAdapter.notifyDataSetChanged();
        rlShopColor.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        rlShopColor.setOnTagSelectListener((parent, selectedList) -> {
            colorPositon = selectedList.get(0);
            strColor = mColors.get(colorPositon).getText();
            // L.e("选中颜色：" + strColor);
            tvColor.setText("\"" + strColor + "\"");
            //获取颜色照片
            initColorShop();
            //查询商品详情
            iterationShop();
        });
    }

    /**
     * 初始化设置
     */
    private void resetBuyButton(boolean isEnable) {
        goInput.setEnabled(isEnable);
        if (isEnable) {
            goInput.setText("预订");
            //  goInput.setBackgroundColor(0xff2196f3);
        } else {
            goInput.setText("缺货");
            // goInput.setBackgroundColor(0xff8c8c8c);
        }
    }

    /**
     * 迭代 选择商品属性
     */
    private void iterationShop() {
        //  选择的内存           选择的版本           选择的颜色
        if (strMemory == null || strVersion == null || strColor == null)
            return;
        //隐藏购买按钮 显示为缺货
        resetBuyButton(false);
        Observable.from(shopLists)
                .filter(childsEntity -> childsEntity.getAttrinfo().getAttrs().get(0).getAttrvalue().equals(strColor))
                .filter(childsEntity -> childsEntity.getAttrinfo().getAttrs().get(1).getAttrvalue().equals(strVersion))
                .filter(childsEntity -> childsEntity.getAttrinfo().getAttrs().get(2).getAttrvalue().equals(strMemory))
                .subscribe(childsEntity -> {
                    L.e(childsEntity.getShopprice());
                    tvPrice.setText("￥" + childsEntity.getShopprice());
                    // ImageHelper.loadImageFromGlide(mActivity, Constant.IMAGE_URL + childsEntity.getShowimg(), ivShopPhoto);
                    L.e("已找到商品：" + childsEntity.getName() + " id:" + childsEntity.getPid());
                    selectGoods = childsEntity;
                    tvShopName.setText(childsEntity.getName());
                    //显示购买按钮
                    resetBuyButton(true);
                    initShopStagesCount++;
                });
    }

    /**
     * 初始化内存
     */
    private void initShopMomery() {
        for (TagInfo mMonery : mMonerys) {
            mMonery.setSelect(false);
            Log.e("  ", "initShopMomery: " + mMonery.getText());
        }
        tvMomey.setText("\"未选择内存\"");
        mMonerys.get(momeryPositon).setSelect(true);
        //-----------------------------创建适配器
        momeryAdapter = new ProperyTagAdapter(mActivity, mMonerys);
        rlShopMomery.setAdapter(momeryAdapter);
        rlShopMomery.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        rlShopMomery.setOnTagSelectListener((parent, selectedList) -> {
            momeryPositon = selectedList.get(0);
            strMemory = mMonerys.get(momeryPositon).getText();
            // L.e("选中内存：" + strMemory);
            iterationShop();
            tvMomey.setText("\"" + strMemory + "\"");
            iterationVersion();
        });
    }

    /**
     * 迭代 获取版本信息
     */
    private void iterationVersion() {
        if (strColor == null || strMemory == null) {
            return;
        }
        // L.e("开始迭代版本");
        Observable.from(mVersions).subscribe(tagInfo -> {
            tagInfo.setChecked(true);
        });
        final List<String> iterationTempVersion = new ArrayList<>();
        //1. 遍历出 这个颜色下的所有手机
        //2. 遍历出 这些手机的所有版本
        Observable.from(shopLists)
                .filter(childsEntity -> childsEntity.getAttrinfo().getAttrs().get(0).getAttrvalue().equals(strColor))
                .filter(childsEntity -> childsEntity.getAttrinfo().getAttrs().get(2).getAttrvalue().equals(strMemory))
                .flatMap(childsEntity -> Observable.from(childsEntity.getAttrinfo().getAttrs()))
                .filter(attrsEntity -> attrsEntity.getAttrname().equals(mActivity.getString(R.string.shop_standard)))
                .subscribe(attrsEntity -> {
                    iterationTempVersion.add(attrsEntity.getAttrvalue());
                });

        Observable.from(mTempVersions).filter(s -> !iterationTempVersion.contains(s)).subscribe(s -> {
            mVersions.get(mTempVersions.indexOf(s)).setChecked(false);
        });
        versionAdapter.notifyDataSetChanged();
        // L.e("迭代版本完成");
    }

    /**
     * 初始化版本
     */
    private void initShopVersion() {
        for (TagInfo mVersion : mVersions) {
            mVersion.setSelect(false);
        }
        tvVersion.setText("\"未选择版本\"");
        mVersions.get(versionPositon).setSelect(true);
        //-----------------------------创建适配器
        versionAdapter = new ProperyTagAdapter(mActivity, mVersions);
        rlShopVersion.setAdapter(versionAdapter);
        rlShopVersion.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        rlShopVersion.setOnTagSelectListener((parent, selectedList) -> {
            versionPositon = selectedList.get(0);
            strVersion = mVersions.get(versionPositon).getText();
            // L.e("选中版本：" + strVersion);
            iterationShop();
            tvVersion.setText("\"" + strVersion + "\"");
        });
    }

    /**
     * 初始化相应的颜色的商品 获得 图片
     */
    private void initColorShop() {
        //初始化 选项数据
        Observable.from(mMonerys).subscribe(tagInfo -> {
            tagInfo.setChecked(true);
        });
        L.e("开始筛选颜色下的内存----------------------------------------------------------------------------------");
        final List<String> tempColorMemery = new ArrayList<>();
        //筛选内存
        Observable.from(shopLists)
                .filter(childsEntity -> childsEntity.getAttrinfo().getAttrs().get(0).getAttrvalue().equals(strColor))
                .flatMap(childsEntity -> Observable.from(childsEntity.getAttrinfo().getAttrs()))
                .filter(attrsEntity -> mActivity.getString(R.string.shop_monery).equals(attrsEntity.getAttrname()))
                .subscribe(attrsEntity -> {
                    tempColorMemery.add(attrsEntity.getAttrvalue());
                    // L.e("内存："+attrsEntity.getAttrvalue());
                });

        Observable.from(mTempMonerys)
                .filter(s -> !tempColorMemery.contains(s))
                .subscribe(s -> {
                    L.e("没有的内存：" + s);
                    mMonerys.get(mTempMonerys.indexOf(s)).setChecked(false);
                });
        momeryAdapter.notifyDataSetChanged();
        L.e("筛选颜色下的内存完成----------------------------------------------------------------------------------");

        //获取颜色的照片
         ImageHelper.loadImageFromGlide(mActivity, mImages.get(tempImageColor.indexOf(strColor)), ivShopPhoto);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close://关闭弹窗
                mBottomSheetDialog.dismiss();
                break;
            case R.id.btn_buy_input_message://下一步
                if (selectGoods == null) {
                    callBack.complete("请选择商品");
                    return;
                }
                //输入订单信息
                //inputOrderMessage();
                break;
            case R.id.btn_shop_cut://减少
                if (shopNum > 1)
                    shopNum--;
                else {
                    callBack.complete("最少要有一个");
                }
                tvNum.setText(shopNum + "");
                break;
            case R.id.btn_shop_add://增加
                if (shopNum < 99)
                    shopNum++;
                else
                    callBack.complete("已经达到上限了");
                tvNum.setText(shopNum + "");
                break;

        }
    }

    /**
     * 初始化商品信息
     * <li>1. 提取所有的属性</li>
     * <li>2. 提取所有颜色的照片</li>
     */
    private void initGoodsInfo() {
        //所有的颜色
        mColors = new ArrayList<>();
        //筛选过程中临时存放颜色
        mTempColors = new ArrayList<>();
        //所有的内存
        mMonerys = new ArrayList<>();
        //筛选过程中临时的内存
        mTempMonerys = new ArrayList<>();
        //所有的版本
        mVersions = new ArrayList<>();
        //筛选过程中的临时版本
        mTempVersions = new ArrayList<>();
        //获取到所有的商品
        shopLists = responseDto.getMsg().getChilds();
        callBack.refreshSuccess("￥" + responseDto.getPricemin() + " - " + responseDto.getPricemax(), responseDto.getMsg().getParent().getName());
        callBack.parentName(responseDto.getMsg().getParent().getName());
        //遍历商品
        Observable.from(shopLists)
                //转换对象 获取所有商品的属性集合
                .flatMap(childsEntity -> Observable.from(childsEntity.getAttrinfo().getAttrs()))
                .subscribe(attrsEntity -> {
                    //判断颜色
                    if (mActivity.getString(R.string.shop_color).equals(attrsEntity.getAttrname()) && !mTempColors.contains(attrsEntity.getAttrvalue())) {
                        mColors.add(new TagInfo(attrsEntity.getAttrvalue()));
                        mTempColors.add(attrsEntity.getAttrvalue());
                    }
                    //判断制式
                    if (mActivity.getString(R.string.shop_standard).equals(attrsEntity.getAttrname()) && !mTempVersions.contains(attrsEntity.getAttrvalue())) {
                        mVersions.add(new TagInfo(attrsEntity.getAttrvalue()));
                        mTempVersions.add(attrsEntity.getAttrvalue());
                    }
                    //判断内存
                    if (mActivity.getString(R.string.shop_monery).equals(attrsEntity.getAttrname()) && !mTempMonerys.contains(attrsEntity.getAttrvalue())) {
                        mMonerys.add(new TagInfo(attrsEntity.getAttrvalue()));
                        mTempMonerys.add(attrsEntity.getAttrvalue());
                    }
                });

        // 提取出 每种颜色的照片
        tempImageColor = new ArrayList<>();
        mImages = new ArrayList<>();
        //遍历所有的商品列表
        Observable.from(shopLists)
                .subscribe(childsEntity -> {
                    String color = childsEntity.getAttrinfo().getAttrs().get(0).getAttrvalue();
                    if (!tempImageColor.contains(color)) {
                        mImages.add(childsEntity.getShowimg());
                        tempImageColor.add(color);
                    }
                });
        // 提取出 每种颜色的照片

        //通知图片
        callBack.changeData(mImages, "￥" + responseDto.getPricemin() + " - " + responseDto.getPricemax());
        callBack.complete(null);
    }

}
