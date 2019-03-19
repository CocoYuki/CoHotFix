package com.yirong.hotfixtest;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.yirong.hotfixtest.utils.FixDexUtils;

public class BaseApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        //加载热修复Dex文件
        FixDexUtils.loadFixDex(base);
    }
}
