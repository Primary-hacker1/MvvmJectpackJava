package com.justsafe.just.di.modules;

import android.app.Application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;


import com.justsafe.just.di.factory.ViewAtivityModelFactory;
import com.justsafe.just.vm.JUSTMeViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * 给Activity的
 */
@Module
public class ActivityModules {
    private final AppCompatActivity activity;

    public ActivityModules(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    AppCompatActivity activity() {
        return this.activity;
    }


    @Provides
    ViewAtivityModelFactory provideViewModelFactory(Application application) {
        return new ViewAtivityModelFactory(application);
    }

    @Provides
    JUSTMeViewModel providedJUSTMeViewModel(AppCompatActivity activity, ViewAtivityModelFactory factory) {
        return ViewModelProviders.of(activity, factory).get(JUSTMeViewModel.class);
    }


}
