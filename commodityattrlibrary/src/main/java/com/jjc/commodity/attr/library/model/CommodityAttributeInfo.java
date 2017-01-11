package com.jjc.commodity.attr.library.model;

import java.util.List;

/**
 * Created by 江俊超 on 2017/1/11 0011.
 * <p>Gihub https://github.com/aohanyao</p>
 * <p>商品属性对象</p>
 */
public class CommodityAttributeInfo {
    private String mAttrName;
    private List<String> mAttrValues;

    public CommodityAttributeInfo(String mAttrName, List<String> mAttrValues) {
        this.mAttrName = mAttrName;
        this.mAttrValues = mAttrValues;
    }

    public String getmAttrName() {
        return mAttrName;
    }

    public void setmAttrName(String mAttrName) {
        this.mAttrName = mAttrName;
    }

    public List<String> getmAttrValues() {
        return mAttrValues;
    }

    public void setmAttrValues(List<String> mAttrValues) {
        this.mAttrValues = mAttrValues;
    }
}
