package com.abdullah.coindataprovider.interactor;

import com.abdullah.coindataprovider.model.CoinResponse;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by abdullah on 13.1.2018.
 */

public interface CoinInteractor {
    Single<List<CoinResponse>> getCoinRateByCurrency(String coinId, String convertType);
}
