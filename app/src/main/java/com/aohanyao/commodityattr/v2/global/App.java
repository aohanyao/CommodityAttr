package com.aohanyao.commodityattr.v2.global;

import android.app.Application;

/**
 * Created by 江俊超 on 2017/1/11 0011.
 * <p>Gihub https://github.com/aohanyao</p>
 */

public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);
    }
}
