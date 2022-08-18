package com.justsafe.just.di.components;

import android.app.Application;
import android.content.Context;

import androidx.fragment.app.Fragment;

import com.justsafe.just.di.modules.ApplicationFragmentModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationFragmentModule.class)
public interface ApplicationFragmentComponent {
    void inject(Fragment fragment);
    //Exposed to sub-graphs.
    Context context();

    Application application();


}
