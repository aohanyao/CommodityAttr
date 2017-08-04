package com.aohanyao.commodity.library;

import com.aohanyao.commodity.library.inf.AttributesValueInf;
import com.aohanyao.commodity.library.inf.OnSelectCommodityListener;
import com.aohanyao.commodity.library.model.CommoditySpiderInfo;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by 江俊超 on 8/3/2017.
 * <p>版本:1.0.0</p>
 * <b>说明<b><br/>
 * <li>商品蜘蛛筛选帮助类</li>
 */
public class CommoditySpiderHelper {
    private List<CommoditySpiderInfo> commoditySpiderInfos;
    private OnSelectCommodityListener onSelectCommodityListener;
    private Map<String, Set<String>> mSortValues;

    public CommoditySpiderHelper(List<CommoditySpiderInfo> commoditySpiderInfos, OnSelectCommodityListener onSelectCommodityListener) {
        this.commoditySpiderInfos = commoditySpiderInfos;
        this.onSelectCommodityListener = onSelectCommodityListener;
        initSpider();
    }

    /**
     * 初始化蜘蛛
     */
    private void initSpider() {
        Set<String> mAllAttrNames = new HashSet<>();
        //将所有的属性名筛选出来
        for (int i = 0; i < commoditySpiderInfos.size(); i++) {
            CommoditySpiderInfo commoditySpiderInfo = commoditySpiderInfos.get(i);
            List<? extends AttributesValueInf> attributes = commoditySpiderInfo.getAttributes();
            //属性键值对
            Map<String, String> mValues = new TreeMap<>();
            for (AttributesValueInf attribute : attributes) {
                mAllAttrNames.add(attribute.getAttributesName());

                mValues.put(attribute.getAttributesName(), attribute.getAttributesValue());
            }
            commoditySpiderInfo.setFilterValues(mValues);
        }

        //存放属性名
        mSortValues = new TreeMap<>();
        for (String mAllAttrName : mAllAttrNames) {
            mSortValues.put(mAllAttrName, new HashSet<String>());
        }
        //筛选出属性名下所有的属性值
        for (CommoditySpiderInfo commoditySpiderInfo : commoditySpiderInfos) {
            for (AttributesValueInf attributesValueInf : commoditySpiderInfo.getAttributes()) {
                //颜色    --   红色  黑色  等
                mSortValues.get(attributesValueInf.getAttributesName()).add(attributesValueInf.getAttributesValue());
            }
        }

        if (onSelectCommodityListener != null) {
            onSelectCommodityListener.sortValues(mSortValues);
        }
    }

    /**
     * 开始筛选
     *
     * @param params
     */
    public void filter(Map<String, String> params) {
        boolean isFound;
        //遍历蜘蛛
        for (CommoditySpiderInfo commoditySpiderInfo : commoditySpiderInfos) {
            isFound = false;
            //遍历商品属性
            for (Map.Entry<String, String> stringStringEntry : commoditySpiderInfo.getFilterValues().entrySet()) {
                //保存起来的Key
                String key = stringStringEntry.getKey();
                String value = stringStringEntry.getValue();
                //参数对比
                if (!value.equals(params.get(key))) {
                    isFound = false;
                    break;
                }
                isFound = true;
            }
            if (isFound && onSelectCommodityListener != null) {
                onSelectCommodityListener.onSelectCommodityListener(commoditySpiderInfo.getCommodityInf());
                break;
            }
        }

    }



}
