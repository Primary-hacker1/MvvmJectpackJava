package com.justsafe.just.di.components;




import com.justsafe.just.di.factory.ViewFragmentModuleFactory;
import com.justsafe.just.di.modules.ViewFragmentModelModule;
import com.justsafe.just.di.scope.PerFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = ApplicationComponent.class,modules = ViewFragmentModelModule.class)
public interface ViewFragmentModelComponent {
    void inject(ViewFragmentModuleFactory viewFragmentModuleFactory);
}


