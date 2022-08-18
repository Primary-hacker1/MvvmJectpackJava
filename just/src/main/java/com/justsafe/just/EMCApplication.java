package com.justsafe.just;

import android.app.Application;

import com.justsafe.just.di.components.ApplicationComponent;
import com.justsafe.just.di.components.DaggerApplicationComponent;
import com.justsafe.just.di.modules.ApplicationModule;

import dagger.android.support.DaggerApplication;

public class EMCApplication extends Application {

    private static EMCApplication instance;
    private ApplicationComponent mApplicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initializeInjector();
        initBugly();

    }

    public static final String BUGLY_APPID = "c97b0b79c6";

    private void initBugly() {
        // bugly上报
        //CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
        //strategy.setAppVersion(TIMManager.getInstance().getVersion());
        //CrashReport.initCrashReport(getApplicationContext(), PrivateConstants.BUGLY_APPID, true, strategy);
//        CrashReport.initCrashReport(getApplicationContext(), BUGLY_APPID, false);

    }


    public static EMCApplication getApplication() {
        return instance;
    }

    private void initializeInjector() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
