package com.abdullah.coindataprovider.view;

import com.abdullah.coindataprovider.interactor.impl.CoinInteractorImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by abdullah on 14.1.2018.
 */
@Module
public class MainActivityModule {
    @Provides
    MainActivityContract.Presenter providePresenter(CoinInteractorImpl coinInteractor) {
        return new MainActivityPresenterImpl(coinInteractor);
    }
}
