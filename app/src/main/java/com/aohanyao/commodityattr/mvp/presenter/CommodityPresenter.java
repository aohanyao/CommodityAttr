package com.aohanyao.commodityattr.mvp.presenter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aohanyao.commodity.library.CommoditySpiderHelper;
import com.aohanyao.commodity.library.inf.OnSelectCommodityListener;
import com.aohanyao.commodity.library.model.CommoditySpiderInfo;
import com.aohanyao.commodityattr.adapter.PropertyTagAdapter;
import com.aohanyao.commodityattr.model.ShopDetailResponseDto;
import com.aohanyao.commodityattr.model.TagInfo;
import com.aohanyao.commodityattr.mvp.contract.CommodityContract;
import com.aohanyao.commodityattr.ui.FlowTagLayout;
import com.aohanyao.commodityattr.ui.MyDialog;
import com.aohanyao.commodityattr.ui.OnTagSelectListener;
import com.aohanyao.commodityattr.util.FileUtils;
import com.aohanyao.commodityattr.util.ImageHelper;
import com.aohanyao.commodityattr.util.L;
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
        implements OnSelectCommodityListener<ShopDetailResponseDto.MsgBean.CommodityBean> {
    /**
     * 弹窗
     */
    private MyDialog mBottomSheetDialog;

    /**
     * 图像
     */
    private ImageView ivShopPhoto;
    /**
     * 筛选到的所有的属性
     */
    private Map<String, Set<String>> sortValues = new TreeMap<>();
    /**
     * 存放所有适配器中的数据
     */
    private Map<String, List<TagInfo>> mDatas = new TreeMap<>();
    /**
     * 所有的适配器
     */
    private Map<String, PropertyTagAdapter> mAdapters = new TreeMap<>();
    /**
     * 筛选帮助类
     */
    private CommoditySpiderHelper mCommoditySpiderHelper;
    /**
     * 已经筛选到的属性
     */
    private Map<String, String> mSelectAttr = new TreeMap<>();

    /**
     * 解析到的数据 Test数据
     */
    private ShopDetailResponseDto responseDto;

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
        //清除已经筛选的属性
        mSelectAttr.clear();

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
        //-------颜色、内存、是否分期 数量
        TextView tvColor = (TextView) contentView.findViewById(R.id.tv_shop_color);
        TextView tvMomey = (TextView) contentView.findViewById(R.id.tv_shop_momery);
        TextView tvVersion = (TextView) contentView.findViewById(R.id.tv_shop_version);
        TextView tvShopCountry = (TextView) contentView.findViewById(R.id.tv_shop_country);
        TextView tvPrice = (TextView) contentView.findViewById(R.id.tv_shop_price);
        TextView tvShopName = (TextView) contentView.findViewById(R.id.tv_shop_name);
        ivShopPhoto = (ImageView) contentView.findViewById(R.id.iv_shop_photo);
        //-------颜色、内存、是否分期 数量


        FlowTagLayout rlShopColor = (FlowTagLayout) contentView.findViewById(R.id.rl_shop_color);
        //--------------------------商品颜色

        //--------------------------内存列表
        FlowTagLayout rlShopMomery = (FlowTagLayout) contentView.findViewById(R.id.rl_shop_momery);
        //--------------------------内存列表

        //-------------------------制式版本
        FlowTagLayout rlShopVersion = (FlowTagLayout) contentView.findViewById(R.id.rl_shop_version);

        FlowTagLayout rlShopCountry = (FlowTagLayout) contentView.findViewById(R.id.rl_shop_country);

        //关闭
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        contentView.findViewById(R.id.btn_buy_input_message).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        ///////////初始化流式布局/////////初始化流式布局//--------初始化流式布局-------------
        //机身颜色 机身颜色机身颜色机身颜色机身颜色机身颜色 start
        initAdapter(tvColor, "颜色", rlShopColor);
        //机身颜色 机身颜色机身颜色机身颜色机身颜色机身颜色机身颜色end

        //机身内存机身内存机身内存机身内存机身内存机身内存机身内存机身内存机身内存
        initAdapter(tvMomey, "内存", rlShopMomery);
        //机身内存机身内存机身内存机身内存机身内存机身内存机身内存机身内存机身内存

        //版本版本版本版本版本版本版本版本版本版本版本版本版本版本版本版本版本版本版本
        initAdapter(tvVersion, "制式", rlShopVersion);
        //版本版本版本版本版本版本版本版本版本版本版本版本版本版本版本版本版本版本版本

        //国家国家国家国家国家国家国家国家国家国家国家国家国家国家国家国家国家国家国家
        initAdapter(tvShopCountry, "国家", rlShopCountry);
        //国家国家国家国家国家国家国家国家国家国家国家国家国家国家国家国家国家国家国家
        ////////////初始化流式布局////////初始化流式布局//---------初始化流式布局------------


        //------------名字 价格
        tvShopName.setText(responseDto.getMsg().getParent().getName());
        tvPrice.setText("￥" + responseDto.getPricemin());
        //------------名字 价格
        ImageHelper.loadImageFromGlide((Activity) view, responseDto.getMsg().getParent().getShowimg(), ivShopPhoto);
        mBottomSheetDialog.show();
        // TODO 这里的代码全部是用来初始化 弹窗之类的，不用过于在意  end
    }

    /**
     * 初始化适配器
     *
     * @param tvTip 提示文本
     * @param key   key
     * @param ftl   布局
     */
    private void initAdapter(final TextView tvTip, final String key, final FlowTagLayout ftl) {
        //属性筛选出来
        final List<TagInfo> mAttrs = new ArrayList<>();
        //存放数据
        mDatas.put(key, mAttrs);
        //属性
        for (String attr : sortValues.get(key)) {
            mAttrs.add(new TagInfo(attr));
        }
        //适配器
        PropertyTagAdapter mAdapter = new PropertyTagAdapter(mActivity, mAttrs);
        //存放适配器
        mAdapters.put(key, mAdapter);
        //设置适配器
        ftl.setAdapter(mAdapter);
        //通知适配器
        mAdapter.notifyDataSetChanged();
        //设置模式
        ftl.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        //监听
        ftl.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    //获取悬着到的
                    TagInfo tagInfo = mAttrs.get(selectedList.get(0));
                    //设置
                    tvTip.setText(tagInfo.getText());
                    //添加到参数中
                    mSelectAttr.put(key, tagInfo.getText());
                } else {
                    //放弃点击了
                    mSelectAttr.remove(key);
                    //清除所有的选择
                    ftl.clearAllOption();
                }
                L.e("剩余属性:" + mSelectAttr.toString() + "   ftl:" + ftl.toString());
                //开始筛选属性
                filterAttr(mSelectAttr);
            }
        });
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
        responseDto = new Gson().fromJson(json, ShopDetailResponseDto.class);
        List<ShopDetailResponseDto.MsgBean.CommodityBean> childs = responseDto.getMsg().getChilds();

        List<CommoditySpiderInfo> commoditySpiderInfos = new ArrayList<>();
        for (ShopDetailResponseDto.MsgBean.CommodityBean child : childs) {

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

    @Override
    public void sortValues(Map<String, Set<String>> sortValues) {
        //拿到了数据，将数据保存起来
        this.sortValues = sortValues;
    }

    @Override
    public void sortAttrs(Map<String, Set<String>> sortAttrs) {
//        Toast.makeText((Activity) view, "已筛选到剩余的属性:" + sortAttrs.toString(), Toast.LENGTH_SHORT).show();
        Log.e("sortAttrs: ", sortAttrs.toString());

        //开始遍历
        for (Map.Entry<String, Set<String>> entry : sortAttrs.entrySet()) {
            //获取数据源
            List<TagInfo> mData = mDatas.get(entry.getKey());
            //遍历没一个数据
            for (int i = 0; i < mData.size(); i++) {
                TagInfo info = mData.get(i);
                //是否包含
                info.setChecked(entry.getValue().contains(info.getText()));
            }
            //通知适配器更改
            mAdapters.get(entry.getKey()).notifyDataSetChanged();
        }

    }

    @Override
    public void onSelectCommodityListener(ShopDetailResponseDto.MsgBean.CommodityBean commodity) {

    }
}
