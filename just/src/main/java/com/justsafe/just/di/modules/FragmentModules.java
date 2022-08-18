package com.justsafe.just.di.modules;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.justsafe.just.di.factory.ViewFragmentModuleFactory;
import com.justsafe.just.vm.JUSTMeViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModules {

    private final Fragment fragment;

    public FragmentModules(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    Fragment fragment() {
        return this.fragment;
    }

    @Provides
    ViewFragmentModuleFactory provideViewModelFactory() {
        return new ViewFragmentModuleFactory(fragment.getActivity().getApplication());
    }

    @Provides
    JUSTMeViewModel providedEMCMeViewModel(Fragment fragment, ViewFragmentModuleFactory factory) {
        return ViewModelProviders.of(fragment, factory).get(JUSTMeViewModel.class);
    }


}
