package com.abdullah.coinlistapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.wear.widget.WearableRecyclerView;
import android.support.wearable.activity.WearableActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.abdullah.coindataprovider.constant.StaticFields;
import com.abdullah.coindataprovider.di.DaggerNetworkComponent;
import com.abdullah.coindataprovider.di.NetworkComponent;
import com.abdullah.coindataprovider.di.NetworkModule;
import com.abdullah.coindataprovider.model.CoinResponse;
import com.abdullah.coindataprovider.view.MainActivityContract;
import com.abdullah.coinlistapp.adapter.CoinListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends WearableActivity implements MainActivityContract.View {

    @BindView(R.id.recycler_view_coin_list)
    WearableRecyclerView recyclerViewCoinList;

    @BindView(R.id.text_coin_symbol)
    TextView coinSymbolText;

    @Inject
    MainActivityContract.Presenter presenter;

    private CoinListAdapter coinListAdapter;

    private List<CoinResponse> coinResponseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Unbinder unbinder = ButterKnife.bind(this);
        initDagger();
        recyclerViewCoinList.setLayoutManager(new LinearLayoutManager(this));
        coinListAdapter = new CoinListAdapter();
        recyclerViewCoinList.setAdapter(coinListAdapter);
        presenter.attachView(this, unbinder);

        // Enables Always-on
        setAmbientEnabled();
    }

    private void initDagger() {
        DaggerMainActivityComponent.builder().networkComponent(getNetworkComponent())
                .build().inject(this);
    }

    private NetworkComponent getNetworkComponent() {
        return DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule()).build();
    }

    @Override
    public void showLastRate(CoinResponse coinResponse) {
        coinResponseList = new ArrayList<>();
        coinSymbolText.setText(StaticFields.ONE_STR + StaticFields.EMPTY + coinResponse.getSymbol());
        coinResponseList.add(coinResponse);
        coinListAdapter.swapItems(coinResponseList);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
