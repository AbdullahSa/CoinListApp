package com.abdullah.coindataprovider.interactor.impl;

import com.abdullah.coindataprovider.interactor.CoinInteractor;
import com.abdullah.coindataprovider.model.CoinResponse;
import com.abdullah.coindataprovider.service.CoinService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by abdullah on 13.1.2018.
 */

public class CoinInteractorImpl implements CoinInteractor {

    private CoinService coinService;

    @Inject
    CoinInteractorImpl(CoinService coinService) {
        this.coinService = coinService;
    }

    @Override
    public Single<List<CoinResponse>> getCoinRateByCurrency(String coinId, String convertType) {
        return coinService.getCoinResponse(coinId, convertType);
    }
}
