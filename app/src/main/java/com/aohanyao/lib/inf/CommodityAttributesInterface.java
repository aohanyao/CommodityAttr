package com.aohanyao.lib.inf;

/**
 * Created by 江俊超 on 7/31/2017.
 * <p>版本:1.0.0</p>
 * <b>说明<b><br/>
 * <li>商品属性接口</li>
 */
public interface CommodityAttributesInterface {
    /**
     * 获取属性值
     *
     * @return
     */
    String getCommodityAttributeValue();

    /**
     * 获取属性名称
     *
     * @return
     */
    String getCommodityAttributeName();

    /**
     * 获取商品名称
     *
     * @return
     */
    String getCommodityName();

    /**
     * 商品的下标
     *
     * @return
     */
    int getCommodityIndex();

}
