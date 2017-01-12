package com.jjc.commodity.attr.library.inf;

/**
 * Created by 江俊超 on 2017/1/11 0011.
 * <p>Gihub https://github.com/aohanyao</p>
 * <p>商品属性的接口</p>
 */
public interface CommodityAttrInf {

    /**
     * 子类初始化 属性值<br/>
     * <b>例如：白色</b><br/>
     * <b>例如：4G</b><br/>
     * <b>例如：全网通</b><br/>
     *
     * @return
     */
    String getmCommodityAttrBaseInfValue();

    /**
     * 子类初始化 属性名称<br/>
     * <b>例如：颜色</b><br/>
     * <b>例如：容量</b><br/>
     * <b>例如：版本</b><br/>
     *
     * @return
     */
    String getmCommodityAttrBaseInfName();


}
