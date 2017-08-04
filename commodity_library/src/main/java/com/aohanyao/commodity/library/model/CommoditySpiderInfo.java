package com.aohanyao.commodity.library.model;

import com.aohanyao.commodity.library.inf.AttributesValueInf;
import com.aohanyao.commodity.library.inf.CommodityInf;

import java.util.List;
import java.util.Map;

/**
 * Created by 江俊超 on 8/3/2017.
 * <p>版本:1.0.0</p>
 * <b>说明<b><br/>
 * <li>商品蜘蛛信息</li>
 */
public class CommoditySpiderInfo {
    //首先 我需要一个 商品对象
    private CommodityInf commodityInf;
    //然后 我需要这个商品的所有属性
    private List<? extends AttributesValueInf> attributes;
    private Map<String, String> filterValues;

    public Map<String, String> getFilterValues() {
        return filterValues;
    }

    public void setFilterValues(Map<String, String> filterValues) {
        this.filterValues = filterValues;
    }

    public CommoditySpiderInfo(CommodityInf commodityInf, List<? extends AttributesValueInf> attributes) {
        this.commodityInf = commodityInf;
        this.attributes = attributes;
    }

    public CommodityInf getCommodityInf() {
        return commodityInf;
    }

    public void setCommodityInf(CommodityInf commodityInf) {
        this.commodityInf = commodityInf;
    }

    public List<? extends AttributesValueInf> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<? extends AttributesValueInf> attributes) {
        this.attributes = attributes;
    }
}

