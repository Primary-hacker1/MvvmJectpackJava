package com.justsafe.libarch.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

public class MarketUtil {


    public static String TEST = "com.ss.android.ugc.aweme";
    public static String PACKAGE = "com.android.xg.elab";


    public static String HUAWEI = "com.huawei.appmarket";
    public static String XIAOMI = "com.xiaomi.market";
    public static String GooglePlay = "com.android.vending";
    public static String A360 = "com.qihoo.appstore";
    public static String A = "com.xiaomi.market";
    public static String wandoujia = "com.android.vending";
    public static String QQ = "com.tencent.android.qqdownloader";


    /**
     * 启动到应用商店app详情界面
     *
     * @param appPkg    目标App的包名
     * @param marketPkg 应用商店包名 ,如果为"" 则由系统弹出应用商店
     *                  列表供用户选择,否则调转到目标市场的应用详情界面，某些应用商店可能会失败
     */
    public static void launchAppDetail(Context context, String appPkg, String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg)) return;
            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg)) {
                intent.setPackage(marketPkg);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void launchWeb(Context context, String path) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(path));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
