package com.abdullah.coinlistapp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.abdullah.coindataprovider.constant.StaticFields;
import com.abdullah.coindataprovider.di.DaggerNetworkComponent;
import com.abdullah.coindataprovider.di.NetworkComponent;
import com.abdullah.coindataprovider.di.NetworkModule;
import com.abdullah.coindataprovider.model.CoinResponse;
import com.abdullah.coindataprovider.view.MainActivityContract;
import com.abdullah.coinlistapp.R;
import com.abdullah.coinlistapp.view.adapter.CoinListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    @BindView(R.id.text_coin_symbol)
    TextView coinSymbolText;

    @BindView(R.id.rv_exchange_rate_list)
    RecyclerView rateExchangeRecyclerView;

    @BindView(R.id.edit_text_coin)
    EditText editTextCoin;

    @Inject
    MainActivityContract.Presenter presenter;

    @OnClick(R.id.button_coin)
    void onSearchCoinRates() {
        presenter.getRatesOfEuroByCoinId(editTextCoin.getText().toString().trim().toLowerCase());
    }

    private CoinListAdapter coinListAdapter;

    private List<CoinResponse> coinResponseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Unbinder unbinder = ButterKnife.bind(this);
        initDagger();
        rateExchangeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        coinListAdapter = new CoinListAdapter();
        rateExchangeRecyclerView.setAdapter(coinListAdapter);
        presenter.attachView(this, unbinder);
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
