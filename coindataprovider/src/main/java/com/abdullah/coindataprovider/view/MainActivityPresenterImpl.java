package com.abdullah.coindataprovider.view;

import android.util.Log;

import com.abdullah.coindataprovider.constant.StaticFields;
import com.abdullah.coindataprovider.interactor.CoinInteractor;
import com.abdullah.coindataprovider.model.CoinResponse;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.abdullah.coindataprovider.constant.CoinType.BITCOIN;
import static com.abdullah.coindataprovider.constant.ExchangeType.EURO;

/**
 * Created by abdullah on 14.1.2018.
 */

public class MainActivityPresenterImpl implements MainActivityContract.Presenter<MainActivityContract.View> {

    private CompositeDisposable compositeDisposable;
    private MainActivityContract.View view;
    private Unbinder unbinder;

    private CoinInteractor coinInteractor;

    MainActivityPresenterImpl(CoinInteractor coinInteractor) {
        this.coinInteractor = coinInteractor;
    }

    @Override
    public void attachView(MainActivityContract.View view, Unbinder unbinder) {
        this.view = view;
        compositeDisposable = new CompositeDisposable();
        this.unbinder = unbinder;
        getRatesOfCoinByEuro(BITCOIN);
    }

    @Override
    public void detachView() {
        Log.d(this.getClass().getSimpleName(), "detachView() called");
        if (this.view != null) {
            view = null;
            compositeDisposable.dispose();
            compositeDisposable = null;
            unbinder.unbind();
            unbinder = null;
        }
    }

    @Override
    public void getRatesOfEuroByCoinId(String id) {
        compositeDisposable.add(coinInteractor.getCoinRateByCurrency(id, EURO)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).toObservable()
                .subscribeWith(new DisposableObserver<List<CoinResponse>>() {
                    @Override
                    public void onNext(List<CoinResponse> coinResponseList) {
                        view.showLastRate(coinResponseList.get(StaticFields.FIRST_ITEM));
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(this.getClass().getSimpleName(), "onComplete: ");
                    }
                }));
    }

    @Override
    public void getRatesOfCoinByEuro(String id) {
        compositeDisposable.add(Observable.interval(StaticFields.FIRST_ITEM, StaticFields.INTERVAL_FIVE, TimeUnit.MINUTES)
                .flatMap(tick -> coinInteractor.getCoinRateByCurrency(id, EURO)
                        .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).toObservable())
                .subscribeWith(new DisposableObserver<List<CoinResponse>>() {
                    @Override
                    public void onNext(List<CoinResponse> coinResponseList) {
                        view.showLastRate(coinResponseList.get(StaticFields.FIRST_ITEM));
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(this.getClass().getSimpleName(), "onComplete: ");
                    }
                }));
    }
}
