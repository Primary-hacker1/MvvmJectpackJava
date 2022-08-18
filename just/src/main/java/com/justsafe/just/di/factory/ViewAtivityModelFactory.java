package com.justsafe.just.di.factory;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import com.justsafe.just.EMCApplication;
import com.justsafe.just.di.components.DaggerViewModelComponent;
import com.justsafe.just.vm.JUSTMeViewModel;

import javax.inject.Inject;

/**
 * A creator is used to inject the product ID into the ViewModel
 * <p>
 * This creator is to showcase how to inject dependencies into ViewModels. It's not
 * actually necessary in this case, as the product ID can be passed in a public method.
 * 给Activity提供
 */
public class ViewAtivityModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final Context mApplication;

    @Inject
    JUSTMeViewModel justMeViewModel;

    @Inject
    public ViewAtivityModelFactory(Context application) {
        mApplication = application;
        initializeInjector();
    }

    private void initializeInjector() {
        DaggerViewModelComponent.builder()
                .applicationComponent(((EMCApplication) mApplication).getApplicationComponent())
                .build().inject(this);
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(JUSTMeViewModel.class)) {
            return (T) justMeViewModel;
        }else {
            return doCreate(modelClass);
        }
    }

    protected <T extends ViewModel> T doCreate(Class<T> modelClass) {

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
