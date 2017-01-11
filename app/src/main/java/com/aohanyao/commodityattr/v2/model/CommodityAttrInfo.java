package com.aohanyao.commodityattr.v2.model;

import com.jjc.commodity.attr.library.inf.CommodityAttrInf;

/**
 * Created by 江俊超 on 2017/1/11 0011.
 * <p>Gihub https://github.com/aohanyao</p>
 * <b>商品属性对象</b>
 */
public class CommodityAttrInfo implements CommodityAttrInf {
    /**
     * attrvalue : 流光金
     * attrname : 颜色
     */

    private String attrvalue;
    private String attrname;

    public CommodityAttrInfo() {
        super();
    }

    public String getAttrvalue() {
        return attrvalue;
    }

    public void setAttrvalue(String attrvalue) {
        this.attrvalue = attrvalue;
    }

    public String getAttrname() {
        return attrname;
    }

    public void setAttrname(String attrname) {
        this.attrname = attrname;
    }


    @Override
    public String getmCommodityAttrBaseInfValue() {
        return getAttrvalue();
    }

    @Override
    public String getmCommodityAttrBaseInfName() {
        return getAttrname();
    }
}
