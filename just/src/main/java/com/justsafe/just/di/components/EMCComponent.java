package com.justsafe.just.di.components;



import com.justsafe.just.di.modules.ActivityModules;
import com.justsafe.just.di.scope.PerActivity;
import com.justsafe.libview.MainActivity;

import org.jetbrains.annotations.NotNull;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModules.class})
public interface EMCComponent/* extends ApplicationComponent*/ {

    void inject(@NotNull MainActivity emcMainActivity);
}
