package com.abdullah.coindataprovider.di;

import com.abdullah.coindataprovider.interactor.impl.CoinInteractorImpl;

import dagger.Component;

/**
 * Created by abdullah on 13.1.2018.
 */
@Component(modules = NetworkModule.class)
public interface NetworkComponent {
    CoinInteractorImpl coinInteractor();
}
