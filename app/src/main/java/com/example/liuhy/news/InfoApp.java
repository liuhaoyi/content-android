package com.example.liuhy.news;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.hybrid.UMHBCommonSDK;
import com.umeng.socialize.PlatformConfig;

public class InfoApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        UMHBCommonSDK.setLogEnabled(true);
        UMConfigure.init(this,"5c05ed2eb465f504a6000024"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");//

        PlatformConfig.setWeixin("wx3a285dcc54c93e7b", "f85ec0892a2e106943c421fa6924d16d");
        //豆瓣RENREN平台目前只能在服务器端配置
//        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad","http://sns.whalecloud.com");
//        PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
//        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
//        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
//        PlatformConfig.setAlipay("2015111700822536");
//        PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");
//        PlatformConfig.setPinterest("1439206");
//        PlatformConfig.setKakao("e4f60e065048eb031e235c806b31c70f");
//        PlatformConfig.setDing("dingoalmlnohc0wggfedpk");
//        PlatformConfig.setVKontakte("5764965","5My6SNliAaLxEm3Lyd9J");
//        PlatformConfig.setDropbox("oz8v5apet3arcdy","h7p2pjbzkkxt02a");
    }
}
