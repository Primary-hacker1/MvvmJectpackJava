package com.justsafe.just.di.modules;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.justsafe.just.EMCApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationFragmentModule {
    private final EMCApplication mApplication;

    public ApplicationFragmentModule(EMCApplication application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.mApplication;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return this.mApplication;
    }


    @Provides
    @Singleton
    Gson provideGson(){
        return new Gson();
    }


}
