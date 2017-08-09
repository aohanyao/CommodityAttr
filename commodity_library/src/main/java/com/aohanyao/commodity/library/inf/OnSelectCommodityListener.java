package com.aohanyao.commodity.library.inf;

import java.util.Map;
import java.util.Set;

/**
 * 商品选择回调
 *
 * @param <T>
 */
public interface OnSelectCommodityListener<T extends CommodityInf> {
    /**
     * 选择完成回调
     *
     * @param commodity 选择到的商品
     */
    void onSelectCommodityListener(T commodity);

    /**
     * 在这里回调已经分好的值
     *
     * @param sortValues 分好类的属性和属性值
     *                   <p>像这样的：<br/>
     *                   {内存=[4GB+64GB, 3GB+32GB],<br/>
     *                   制式=[联通4G, 移动4G版, 全网通版],<br/>
     *                   国家=[美国, 香港, 台湾, 大陆],<br/>
     *                   颜色=[陶瓷白, 琥珀金, 玫瑰金, 皓月银, 钛银灰, 流光金]}</p>
     */
    void sortValues(Map<String, Set<String>> sortValues);

    /**
     * 筛选出回调的属性
     * <p>
     * 也就是说，假如我选择了白色，这个方法会回调白色下的所有内存，版本等
     * </p>
     *
     * @param sortAttrs
     */
    void sortAttrs(Map<String, Set<String>> sortAttrs);
}