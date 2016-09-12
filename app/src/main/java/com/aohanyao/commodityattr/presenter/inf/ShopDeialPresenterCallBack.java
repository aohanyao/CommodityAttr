package com.aohanyao.commodityattr.presenter.inf;

import java.util.List;

/**
 * <p>作者：江俊超 on 2016/6/18 11:44</p>
 * <p>邮箱：aohanyao@gmail.com</p>
 * <p></p>
 */
public interface ShopDeialPresenterCallBack {
    void changeData(List<String> imgUrl, String price);
    void refreshSuccess(String price, String name);
    void parentName(String parentName);
    /**
     * 关闭弹窗,真个请求完成
     */
    void complete(String completeMsg);
}
