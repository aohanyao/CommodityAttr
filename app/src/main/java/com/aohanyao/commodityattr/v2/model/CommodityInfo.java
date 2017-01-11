package com.aohanyao.commodityattr.v2.model;

import com.aohanyao.commodityattr.v2.dto.CommodityInfoAndAttrDto;
import com.jjc.commodity.attr.library.inf.CommodityInfoInf;

import java.util.List;

/**
 * Created by 江俊超 on 2017/1/11 0011.
 * <p>Gihub https://github.com/aohanyao</p>
 * <b>商品信息对象</b>
 */
public class CommodityInfo implements CommodityInfoInf<CommodityAttrInfo> {

    private String pid;
    private String name;
    private String shopprice;
    private String showimg;
    private CommodityInfoAndAttrDto.AttrinfoBean attrinfo;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopprice() {
        return shopprice;
    }

    public void setShopprice(String shopprice) {
        this.shopprice = shopprice;
    }

    public String getShowimg() {
        return showimg;
    }

    public void setShowimg(String showimg) {
        this.showimg = showimg;
    }

    public CommodityInfoAndAttrDto.AttrinfoBean getAttrinfo() {
        return attrinfo;
    }

    public void setAttrinfo(CommodityInfoAndAttrDto.AttrinfoBean attrinfo) {
        this.attrinfo = attrinfo;
    }


    @Override
    public List<CommodityAttrInfo> getmCommodityAttrInfos() {
        return attrinfo.getAttrs();
    }

    @Override
    public String getmCommodityInfoBaseInfPrice() {
        return getShopprice();
    }

    @Override
    public String getmCommodityInfoBaseInfImageUrl() {
        return getShowimg();
    }

    @Override
    public String getmCommodityInfoBaseInfId() {
        return getPid();
    }

    @Override
    public String getmCommodityInfoBaseInfName() {
        return getName();
    }
}
