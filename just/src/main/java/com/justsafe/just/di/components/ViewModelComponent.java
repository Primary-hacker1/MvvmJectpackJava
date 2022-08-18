package com.justsafe.just.di.components;


import com.justsafe.just.di.factory.ViewAtivityModelFactory;
import com.justsafe.just.di.scope.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class)
public interface ViewModelComponent {
    void inject(ViewAtivityModelFactory viewModelFactory);
}
