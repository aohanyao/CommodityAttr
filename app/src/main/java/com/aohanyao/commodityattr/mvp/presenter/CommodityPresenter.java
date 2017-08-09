package com.aohanyao.commodityattr.mvp.presenter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aohanyao.commodity.library.CommoditySpiderHelper;
import com.aohanyao.commodity.library.inf.OnSelectCommodityListener;
import com.aohanyao.commodity.library.model.CommoditySpiderInfo;
import com.aohanyao.commodityattr.adapter.ProperyTagAdapter;
import com.aohanyao.commodityattr.model.TagInfo;
import com.aohanyao.commodityattr.mvp.contract.CommodityContract;
import com.aohanyao.commodityattr.ui.FlowTagLayout;
import com.aohanyao.commodityattr.ui.MyDialog;
import com.aohanyao.commodityattr.ui.OnTagSelectListener;
import com.aohanyao.commodityattr.util.FileUtils;
import com.aohanyao.commodityattr.util.ImageHelper;
import com.aohanyao.commodityattr.model.ShopDeialResponseDto;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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
    Map<String, Set<String>> sortValues = new TreeMap<>();
    private CommoditySpiderHelper mCommoditySpiderHelper;
    private ShopDeialResponseDto resonseDto;
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
    private Activity mActivity;

    public CommodityPresenter(CommodityContract.View view, Activity mActivity) {
        super(view);
        this.mActivity = mActivity;
    }


    @Override
    public void showDialog() {
        //还没有初始化数据
        if (sortValues.size() == 0) {
            return;
        }
        // TODO 这里的代码全部是用来初始化 弹窗之类的，不用过于在意  start
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


        rlShopColor = (FlowTagLayout) contentView.findViewById(R.id.rl_shop_color);
        //--------------------------商品颜色
        //--------------------------内存列表
        rlShopMomery = (FlowTagLayout) contentView.findViewById(R.id.rl_shop_momery);
        //--------------------------内存列表

        //-------------------------制式版本
        rlShopVersion = (FlowTagLayout) contentView.findViewById(R.id.rl_shop_version);

        //------------------增加 减少
        btnCut = (Button) contentView.findViewById(R.id.btn_shop_cut);
        btnAdd = (Button) contentView.findViewById(R.id.btn_shop_add);
        shopNum = 1;
        //------------------增加 减少
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        goInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        ///////////初始化流式布局/////////初始化流式布局//--------初始化流式布局-------------
        initAdapter();
        ////////////初始化流式布局////////初始化流式布局//---------初始化流式布局------------


        //------------名字 价格
        tvShopName.setText(resonseDto.getMsg().getParent().getName());
        tvPrice.setText("￥" + resonseDto.getPricemin());
        //------------名字 价格
        ImageHelper.loadImageFromGlide((Activity) view, resonseDto.getMsg().getParent().getShowimg(), ivShopPhoto);
        mBottomSheetDialog.show();
        // TODO 这里的代码全部是用来初始化 弹窗之类的，不用过于在意  end
    }

    /**
     * 初始化流式布局
     */
    private void initAdapter() {
        final Map<String, String> selectAttr = new TreeMap<>();
        //机身颜色 机身颜色机身颜色机身颜色机身颜色机身颜色 start
        final List<TagInfo> mColors = new ArrayList<>();
        //颜色
        for (String color : sortValues.get("颜色")) {
            mColors.add(new TagInfo(color));
        }
        tvColor.setText("\"未选择颜色\"");
        colorAdapter = new ProperyTagAdapter(mActivity, mColors);
        rlShopColor.setAdapter(colorAdapter);
        colorAdapter.notifyDataSetChanged();
        rlShopColor.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        rlShopColor.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                int index = selectedList.get(0);
                //存放颜色
                selectAttr.put("颜色", mColors.get(index).getText());
                filterAttr(selectAttr);
            }
        });
        //机身颜色 机身颜色机身颜色机身颜色机身颜色机身颜色机身颜色end

        //机身内存机身内存机身内存机身内存机身内存机身内存机身内存机身内存机身内存
        final List<TagInfo> mMonerys = new ArrayList<>();
        for (String monery : sortValues.get("内存")) {
            mMonerys.add(new TagInfo(monery));
        }
        tvMomey.setText("\"未选择内存\"");
        //-----------------------------创建适配器
        momeryAdapter = new ProperyTagAdapter(mActivity, mMonerys);
        rlShopMomery.setAdapter(momeryAdapter);
        momeryAdapter.notifyDataSetChanged();
        rlShopMomery.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        rlShopMomery.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                int index = selectedList.get(0);
                //内存
                selectAttr.put("内存", mMonerys.get(index).getText());
                filterAttr(selectAttr);
            }
        });
        //机身内存机身内存机身内存机身内存机身内存机身内存机身内存机身内存机身内存

        //版本版本版本版本版本版本版本版本版本版本版本版本版本版本版本版本版本版本版本
        final List<TagInfo> mVersions = new ArrayList<>();
        for (String version : sortValues.get("制式")) {
            mVersions.add(new TagInfo(version));
        }
        tvVersion.setText("\"未选择版本\"");
        //-----------------------------创建适配器
        versionAdapter = new ProperyTagAdapter(mActivity, mVersions);
        rlShopVersion.setAdapter(versionAdapter);
        versionAdapter.notifyDataSetChanged();

        rlShopVersion.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        rlShopVersion.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                int index = selectedList.get(0);
                //制式
                selectAttr.put("制式", mVersions.get(index).getText());
                filterAttr(selectAttr);
            }
        });
        //版本版本版本版本版本版本版本版本版本版本版本版本版本版本版本版本版本版本版本
    }


    private void dismiss() {
        try {
            mBottomSheetDialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        //筛选帮助类 里面会直接初始化数据
        mCommoditySpiderHelper = new CommoditySpiderHelper(commoditySpiderInfos, this);
    }

    /**
     * 筛选剩余属性
     *
     * @param params
     */
    public void filterAttr(Map<String, String> params) {
        //筛选剩余属性
        mCommoditySpiderHelper.filterAttr(params);
    }

    private ProperyTagAdapter colorAdapter;
    private ProperyTagAdapter momeryAdapter;
    private ProperyTagAdapter versionAdapter;

    @Override
    public void sortValues(Map<String, Set<String>> sortValues) {
        //拿到了数据，将数据保存起来
        this.sortValues = sortValues;
    }

    @Override
    public void sortAttrs(Map<String, Set<String>> sortAttrs) {
        Toast.makeText((Activity) view, "已筛选到剩余的属性:" + sortAttrs.toString(), Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onSelectCommodityListener(ShopDeialResponseDto.MsgBean.CommodityBean commodity) {

    }
}
