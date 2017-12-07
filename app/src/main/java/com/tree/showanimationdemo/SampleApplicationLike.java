package com.tree.showanimationdemo;

/*
 *  @项目名：  ShowAnimationDemo 
 *  @包名：    com.tree.showanimationdemo
 *  @文件名:   SampleApplicationLike
 *  @创建者:   Administrator
 *  @创建时间:  2017/11/29 11:49
 *  @描述：    TODO
 */

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDex;

import com.meituan.android.walle.WalleChannelReader;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.tinker.TinkerApplicationLike;

public class SampleApplicationLike extends TinkerApplicationLike {

    public static final String TAG = "Tinker.SampleApplicationLike";

    public SampleApplicationLike(Application application,
                                 int tinkerFlags,
                                 boolean tinkerLoadVerifyFlag,
                                 long applicationStartElapsedTime,
                                 long applicationStartMillisTime,
                                 Intent tinkerResultIntent)
    {
        super(application,
              tinkerFlags,
              tinkerLoadVerifyFlag,
              applicationStartElapsedTime,
              applicationStartMillisTime,
              tinkerResultIntent);
    }





    @Override
    public void onCreate() {
        super.onCreate();
        //设定是否设置为开发设备
//        Bugly.setIsDevelopmentDevice(getApplication(),true);

        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        // 调试时，将第三个参数改为true


        String channel = WalleChannelReader.getChannel(getApplication());
        Bugly.setAppChannel(getApplication(), channel);
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        Bugly.init(getApplication(), "afb5e4f859", true);


    }


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);
        // 安装tinker
        // TinkerManager.installTinker(this); 替换成下面Bugly提供的方法
        Beta.installTinker(this);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
        getApplication().registerActivityLifecycleCallbacks(callbacks);
    }
}
