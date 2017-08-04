package com.aohanyao.commodityattr.mvp.contract;

/**
 * Created by 江俊超 on 8/3/2017.
 * <p>版本:1.0.0</p>
 * <b>说明<b><br/>
 * <li></li>
 */
public interface CommodityContract {
    interface View {

    }

    abstract class Presenter<V extends View> {
        protected V view;

        public Presenter(V view) {
            this.view = view;
        }

        public abstract void showDialog();

        public abstract void initData();
    }
}
