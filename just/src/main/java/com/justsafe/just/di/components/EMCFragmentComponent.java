package com.justsafe.just.di.components;





import com.justsafe.just.di.modules.FragmentModules;
import com.justsafe.just.di.scope.PerFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModules.class)
public interface
EMCFragmentComponent {

//    void inject(@NotNull EMCLoginFragment emcLoginFragment);

}
