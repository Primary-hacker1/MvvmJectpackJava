package com.justsafe.just.di.components;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;


import com.justsafe.just.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(AppCompatActivity baseActivity);
    //Exposed to sub-graphs.
    Context context();

    Application application();

}
