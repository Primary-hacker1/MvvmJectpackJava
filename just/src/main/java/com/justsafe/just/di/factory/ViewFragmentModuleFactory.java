package com.justsafe.just.di.factory;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import com.justsafe.just.EMCApplication;
import com.justsafe.just.di.components.DaggerViewFragmentModelComponent;
import com.justsafe.just.vm.JUSTMeViewModel;

import javax.inject.Inject;

public class ViewFragmentModuleFactory extends ViewModelProvider.NewInstanceFactory {

    private final Context mApplication;

    @Inject
    JUSTMeViewModel justMeViewModel;

    @Inject
    public ViewFragmentModuleFactory(Context application) {
        mApplication = application;
        initializeInjector();
    }

    private void initializeInjector() {
        DaggerViewFragmentModelComponent.builder().applicationComponent(((EMCApplication) mApplication).getApplicationComponent())
                .build().inject(this);
    }

    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(JUSTMeViewModel.class)) {
            return (T) justMeViewModel;
        } else {
            return doCreate(modelClass);
        }

    }

    protected <T extends ViewModel> T doCreate(Class<T> modelClass) {
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }


}
