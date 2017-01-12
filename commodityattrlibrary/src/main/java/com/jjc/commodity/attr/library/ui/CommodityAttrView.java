package com.jjc.commodity.attr.library.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jjc.commodity.attr.library.R;
import com.jjc.commodity.attr.library.adapter.AttrValueAdapter;
import com.jjc.commodity.attr.library.inf.CommodityAttrInf;
import com.jjc.commodity.attr.library.inf.CommodityInfoInf;
import com.jjc.commodity.attr.library.inf.OnTagSelectListener;
import com.jjc.commodity.attr.library.model.CommodityAttributeInfo;
import com.jjc.commodity.attr.library.util.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by 江俊超 on 2017/1/11 0011.
 * <p>Gihub https://github.com/aohanyao</p>
 * <p>筛选商品属性的View</p>
 * <b>//TODO 暂时没有想到更好的办法来解决筛选的问题
 * </b>
 * <li>1.将数据存储在数据库中</li>
 */
public class CommodityAttrView<T extends CommodityInfoInf> extends ScrollView {

    /**
     * 所有商品数据
     */
    private List<T> mCommoditys;
    //商品属性
    private CopyOnWriteArrayList<CommodityAttributeInfo> mCommodityAttributeInfos;
    private String mAttrNames[];
    private CopyOnWriteArrayList<String> mAttrValues;
    private String TAG = getClass().getSimpleName();
    private List<AttrValueAdapter> mAttrValueAdapters;
    private int mTouchSlop;
    private int downY;
    /**
     * 已经选中的属性值
     */
    private String[] mSelectAttrValue;

    public CommodityAttrView(Context context) {
        this(context, null);
    }

    public CommodityAttrView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommodityAttrView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    /**
     * 初始化属性
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    private void initAttr(Context context, AttributeSet attrs, int defStyleAttr) {

    }

    /**
     * 设置数据
     */
    public void setCommoditys(@NonNull List<T> datas) {
        this.mCommoditys = datas;
        if (mAttrNames == null || mAttrNames.length == 0) {
            throw new UnsupportedOperationException("You must setCommoditys() before setAttrNames()");
        }
        initData();
    }

    /**
     * 重置所有的集合
     */
    private void resetList() {
        if (this.mCommoditys != null) {
            this.mCommoditys.clear();
            this.mCommoditys = null;
        }
        if (mCommodityAttributeInfos != null) {
            mCommodityAttributeInfos.clear();
            mCommodityAttributeInfos = null;
        }
        if (mAttrValueAdapters != null) {
            mAttrValueAdapters.clear();
            mAttrValueAdapters = null;
        }
        mCommodityAttributeInfos = new CopyOnWriteArrayList<>();
        mAttrValues = new CopyOnWriteArrayList<>();
        mAttrValueAdapters = new ArrayList<>();
    }

    /**
     * 初始化数据
     */
    private void initData() {

        //----------------------------获取属性名称 之下所有的属性值 start
        for (CommodityInfoInf mCommodity : mCommoditys) {//循环整个 商品列表的每个商品
            //获取到商品的所有属性
            List<CommodityAttrInf> mCommodityAttrs = mCommodity.getmCommodityAttrInfos();
            //便利每个属性
            for (CommodityAttrInf attr : mCommodityAttrs) {
                String attrValue = attr.getmCommodityAttrBaseInfValue();
                String attrName = attr.getmCommodityAttrBaseInfName();
                addAttrValue(attrName, attrValue);
            }
        }
        //----------------------------获取属性名称 之下所有的属性值 end
        //初始化筛选的属性值
        mSelectAttrValue = new String[mCommodityAttributeInfos.size()];
        //------测试查看数据是否正确
        for (CommodityAttributeInfo mCommodityAttributeInfo : mCommodityAttributeInfos) {
            Log.e(TAG, "initData: " + mCommodityAttributeInfo.getmAttrName());
            for (String attValue : mCommodityAttributeInfo.getmAttrValues()) {
                Log.e(TAG, "initData: \t\t\t" + attValue);
            }
        }


        initView();

    }

    /**
     * 初始化视图
     */
    private void initView() {
        //先将所有的视图移除
        removeAllViews();
        //添加一个LinearLayout
        LinearLayout mRootLinearLayout = new LinearLayout(getContext());
        mRootLinearLayout.setOrientation(LinearLayout.VERTICAL);
        mRootLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        //分别添加文字
        for (int i = 0; i < mAttrNames.length; i++) {
            View mAttrView = LayoutInflater.from(getContext()).inflate(R.layout.layout_commdity_attr_item, mRootLinearLayout, false);
            TextView tvAttrName = (TextView) mAttrView.findViewById(R.id.tv_attr_name);//属性名称
            tvAttrName.setText(mAttrNames[i]);
            //属性值 列表 start
            FlowTagLayout ftlAttrValue = (FlowTagLayout) mAttrView.findViewById(R.id.rl_commodity_attr_value);
            ftlAttrValue.setTag(i);
            AttrValueAdapter attrValueAdapter = new AttrValueAdapter(getContext());
            attrValueAdapter.isSelectedPosition(0);
            ftlAttrValue.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
            ftlAttrValue.setAdapter(attrValueAdapter);
            attrValueAdapter.onlyAddAll(mCommodityAttributeInfos.get(i).getmAttrValues());
//            mAttrValueAdapters.add(attrValueAdapter);
            ftlAttrValue.setOnTagSelectListener(new OnTagSelectListener() {
                @Override
                public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                    int mAttrValuePosition = (int) parent.getTag();
                    //查询商品
                    queryCommodity(mAttrValuePosition, selectedList.get(0));
                }
            });
            //属性值 列表 end

            mRootLinearLayout.addView(mAttrView);

        }

        addView(mRootLinearLayout);

    }

    /**
     * 选中了 谁 开始筛选商品
     *
     * @param mAttrValuePosition
     * @param integer
     */
    private void queryCommodity(int mAttrValuePosition, Integer integer) {
        CommodityAttributeInfo attributeInfo = mCommodityAttributeInfos.get(mAttrValuePosition);
        //属性名称   属性值
        String mAttrName = attributeInfo.getmAttrName();
        String mAttrValue = attributeInfo.getmAttrValues().get(integer);

        //Log.e(TAG, "queryCommodity: \t\t\t" + mAttrValuePosition + "   mAttrName:" + mAttrName + "  mAttrValue:" + mAttrValue);
        //将用户选择的属性值存放到相对应的的数组中
        mSelectAttrValue[mAttrValuePosition] = mAttrValue;

        if (ArrayUtils.queryArrayValueIsNull(mSelectAttrValue))//每个属性并没有全部选择完毕
            return;

        for (String s : mSelectAttrValue) {
            //  Log.e(TAG, "queryCommodity: \t\t\t\t\t" + s);
        }
        //已经获取到了一个商品所有的属性  开始编译没一个属性名称  获取属性值
        //for (int i = 0; i < mAttrNames.length; i++) {//所有属性名称

        CopyOnWriteArrayList<CommodityInfoInf> mQuery = new CopyOnWriteArrayList<>();

        for (int i = 0; i < mAttrNames.length; i++) {
            //所有商品
            for (CommodityInfoInf mCommodity : mCommoditys) {
                //获取到商品的所有属性
                List<CommodityAttrInf> mCommodityAttrList = mCommodity.getmCommodityAttrInfos();
                //遍历属性列表
                for (CommodityAttrInf commodityAttrInf : mCommodityAttrList) {
                    String attrName = commodityAttrInf.getmCommodityAttrBaseInfName();
                    int indexOf = 0;
                    try {
                        indexOf = ArrayUtils.queryArrayContainElement(mAttrNames, attrName);
                        Log.e(TAG, "queryCommodity: indexOf:" + indexOf + "  attrName:" + attrName + " mSelectAttrValue[indexOf]:" + mSelectAttrValue[indexOf]);
                        if (!mSelectAttrValue[indexOf].equals(commodityAttrInf.getmCommodityAttrBaseInfValue()))
                            continue;
                        mQuery.add(mCommodity);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }


        for (CommodityInfoInf commodityInfoInf : mQuery) {
            //获取到商品的所有属性
            List<CommodityAttrInf> mCommodityAttrList = commodityInfoInf.getmCommodityAttrInfos();
            for (int i = 0; i < mCommodityAttrList.size(); i++) {
                CommodityAttrInf attr = mCommodityAttrList.get(i);
                String attrName = attr.getmCommodityAttrBaseInfName();
                try {
                    int indexOf = ArrayUtils.queryArrayContainElement(mAttrNames, attrName);
                    Log.e(TAG, "queryCommodity: indexOf:" + indexOf + "  attrName:" + attrName + " mSelectAttrValue[indexOf]:" + mSelectAttrValue[indexOf]);
                    if (!mSelectAttrValue[indexOf].equals(attr.getmCommodityAttrBaseInfValue()))
                        continue;
//                    Log.e(TAG, "queryCommodity: "+commodityInfoInf.getmCommodityInfoBaseInfName() );


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //}

    }

    public void qC(List<CommodityAttrInf> mCommodityAttrList, String[] args){

    }


    /**
     * 将属性值存放到集合中
     *
     * @param attrName
     * @param attrValue
     */
    private void addAttrValue(String attrName, String attrValue) {

        for (CommodityAttributeInfo mCommodityAttributeInfo : mCommodityAttributeInfos) {
            //属性名称相同  颜色 版本 内存
            if (attrName.equals(mCommodityAttributeInfo.getmAttrName())) {
                //判断该属性名称之下是否已经有了该属性值
                if (!mCommodityAttributeInfo.getmAttrValues().contains(attrValue)) {//不包含该属性值
                    //添加到集合
                    mCommodityAttributeInfo.getmAttrValues().add(attrValue);
                    mAttrValues.add(attrValue);
                }
            }
        }
    }


    /**
     * 设置属性名称 可以用来进行排序
     *
     * @param mAttrNames
     */
    public void setAttrNames(@NonNull String[] mAttrNames) {
        this.mAttrNames = mAttrNames;
        resetList();
        for (String mAttrName : mAttrNames) {
            mCommodityAttributeInfos.add(new CommodityAttributeInfo(mAttrName, new ArrayList<String>()));

        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) e.getRawY();
                if (Math.abs(moveY - downY) > mTouchSlop) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(e);
    }
}
