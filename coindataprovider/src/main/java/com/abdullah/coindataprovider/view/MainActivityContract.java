package com.abdullah.coindataprovider.view;

import com.abdullah.coindataprovider.model.CoinResponse;

import butterknife.Unbinder;

/**
 * Created by abdullah on 14.1.2018.
 */

public interface MainActivityContract {
    public interface View {
        void showLastRate(CoinResponse coinResponse);

        void onError(String message);
    }

    public interface Presenter<ViewT extends View> {
        void attachView(ViewT viewT, Unbinder unbinder);

        void detachView();

        void getRatesOfCoinByEuro(String id);

        void getRatesOfEuroByCoinId(String id);
    }
}
