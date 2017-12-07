package com.tree.showanimationdemo;

/*
 *  @项目名：  ShowAnimationDemo 
 *  @包名：    com.tree.showanimationdemo
 *  @文件名:   SampleApplication
 *  @创建者:   Administrator
 *  @创建时间:  2017/11/29 11:37
 *  @描述：    TODO
 */

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

public class SampleApplication
        extends TinkerApplication
{
    /**
     * 参数1：tinkerFlags 表示Tinker支持的类型 dex only、library only or all suuport，default: TINKER_ENABLE_ALL
     参数2：delegateClassName Application代理类 这里填写你自定义的ApplicationLike
     参数3：loaderClassName Tinker的加载器，使用默认即可
     参数4：tinkerLoadVerifyFlag 加载dex或者lib是否验证md5，默认为false
     */
    public SampleApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL,
              "com.tree.showanimationdemo.SampleApplicationLike",
              "com.tencent.tinker.loader.TinkerLoader",
              false);
    }
}
