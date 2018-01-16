package com.abdullah.coinlistapp.view;

import com.abdullah.coindataprovider.di.NetworkComponent;
import com.abdullah.coindataprovider.view.MainActivityModule;

import dagger.Component;

/**
 * Created by abdullah on 14.1.2018.
 */
@Component(dependencies = NetworkComponent.class, modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
