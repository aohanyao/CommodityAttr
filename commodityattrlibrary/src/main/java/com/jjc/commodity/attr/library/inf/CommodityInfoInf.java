package com.jjc.commodity.attr.library.inf;

import java.util.List;

/**
 * Created by 江俊超 on 2017/1/11 0011.
 * <p>Gihub https://github.com/aohanyao</p>
 * <p>商品的接口</p>
 */
public  interface CommodityInfoInf<T extends CommodityAttrInf> {

    /**
     * 子类初始化 商品属性
     *
     * @return
     */
      List<T> getmCommodityAttrInfos();

    /**
     * 子类初始化 价格
     *
     * @return
     */
      String getmCommodityInfoBaseInfPrice();

    /**
     * 子类初始化图片
     *
     * @return
     */
      String getmCommodityInfoBaseInfImageUrl();

    /**
     * 子类初始化商品的ID
     *
     * @return
     */
      String getmCommodityInfoBaseInfId();

    /**
     * 子类初始化 商品名称
     *
     * @return
     */
      String getmCommodityInfoBaseInfName();

}
