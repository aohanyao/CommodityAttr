package com.aohanyao.commodity.library;

import android.util.Log;

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
    /**
     * 商品信息
     */
    private List<CommoditySpiderInfo> commoditySpiderInfos;
    /**
     * 筛选结果返回
     */
    private OnSelectCommodityListener onSelectCommodityListener;
    /**
     * 所有的属性名称和值
     */
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

    /***
     * 根据已有的属性筛选剩余的属性
     * <p>
     *     颜色：
     *          白色
     *          <p>
     *     筛选出白色下的内存有哪些，版本有哪些
     *          </p>
     * </p>
     * @param params 已选择的属性
     * ver：2018年2月26日17:20:02<p>
     *               修改成功了算法，能够筛选出来，剩余就是：当前没有这个属性，
     *               要去除选择
     * </p>
     */
    public void filterAttr(Map<String, String> params) {


        //返回所有
        if (params.size() == 0) {
            if (onSelectCommodityListener != null) {
                onSelectCommodityListener.sortAttrs(mSortValues);
            }
            return;
        }

        //存放着已经筛选到了的商品
        Set<CommoditySpiderInfo> selectCommodity = new HashSet<>();


        //遍历蜘蛛 只要和上面属性一致的商品
        for (CommoditySpiderInfo commoditySpiderInfo : commoditySpiderInfos) {
            //属性对比一致的
            int equlasCount = 0;
            //遍历商品属性
            for (Map.Entry<String, String> stringStringEntry : commoditySpiderInfo.getFilterValues().entrySet()) {
                //保存起来的Key
                String key = stringStringEntry.getKey();
                String value = stringStringEntry.getValue();
                //参数中的值与筛选到的值进行对比
                if (value.equals(params.get(key))) {//获取到当前key value相等的商品  颜色  白色  国家
                    //对比 +1
                    equlasCount++;
                }
            }
            //相同次数
            if (equlasCount == params.size()) {
                //添加到集合中
                selectCommodity.add(commoditySpiderInfo);
            }

        }


        //上面已经拿到了所有包含params所有属性的商品了


        //存放已经筛选到的属性值
        Map<String, Set<String>> attrs = new TreeMap<>();
        //遍历已筛选到的商品，获取剩余的属性
        for (CommoditySpiderInfo commoditySpiderInfo : selectCommodity) {
            //遍历商品属性
            for (Map.Entry<String, String> stringStringEntry : commoditySpiderInfo.getFilterValues().entrySet()) {
                //保存起来的Key
                String key = stringStringEntry.getKey();
                String value = stringStringEntry.getValue();

                if (!params.containsKey(key)) {//不包含当前key 就是没有的属性
                    //获取key的value
                    Set<String> attrSet = attrs.get(key);
                    if (attrSet == null) {
                        attrSet = new HashSet<>();
                        //存放属性值
                        attrSet.add(value);
                        //添加到map
                        attrs.put(key, attrSet);
                    } else {
                        //添加值
                        attrSet.add(value);
                    }

                }
            }
        }
        if (onSelectCommodityListener != null) {
            onSelectCommodityListener.sortAttrs(attrs);
        }
    }

    /**
     * 开始筛选商品
     *
     * @param params
     */
    public void filterCommodity(Map<String, String> params) {
        Log.e("filterCommodity: ", "根据属性《" + params.toString() + "》开始筛选商品");
        //遍历蜘蛛
        for (CommoditySpiderInfo commoditySpiderInfo : commoditySpiderInfos) {
            int isFoundCount = 0;
            //遍历商品属性
            for (Map.Entry<String, String> stringStringEntry : commoditySpiderInfo.getFilterValues().entrySet()) {
                //保存起来的Key
                String key = stringStringEntry.getKey();
                String value = stringStringEntry.getValue();
                //参数对比
                if (value.equals(params.get(key))) {
                    isFoundCount++;
                }
            }
            //筛选到  回调商品
            if (isFoundCount == params.size() && onSelectCommodityListener != null) {
                onSelectCommodityListener.onSelectCommodityListener(commoditySpiderInfo.getCommodityInf());
                break;
            }
        }

    }


}
