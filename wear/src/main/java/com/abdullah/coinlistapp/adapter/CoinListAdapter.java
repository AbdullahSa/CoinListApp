package com.abdullah.coinlistapp.adapter;

import android.support.wear.widget.WearableRecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abdullah.coindataprovider.constant.StaticFields;
import com.abdullah.coindataprovider.model.CoinResponse;
import com.abdullah.coinlistapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.abdullah.coindataprovider.constant.ExchangeType.EURO;

/**
 * Created by abdullah on 14.1.2018.
 */

public class CoinListAdapter extends WearableRecyclerView.Adapter<CoinListAdapter.CoinViewHolder> {

    private List<CoinResponse> coinResponseList;
    private Unbinder unbinder;

    public CoinListAdapter() {
    }

    @Override
    public CoinListAdapter.CoinViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CoinViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_coin, parent, false));
    }

    @Override
    public void onBindViewHolder(CoinListAdapter.CoinViewHolder holder, int position) {
        final CoinResponse coinResponse = coinResponseList.get(position);
        holder.priceText.setText(coinResponse.getPriceEuro() + StaticFields.EMPTY + EURO);
        holder.percentText.setText(coinResponse.getPercentChangeDaily());
    }

    @Override
    public int getItemCount() {
        return coinResponseList == null ? 0 : coinResponseList.size();
    }

    public void addItems(List<CoinResponse> additionCoinResponseList) {
        if (this.coinResponseList == null) {
            this.coinResponseList = new ArrayList<>();
        }
        if (additionCoinResponseList != null && !additionCoinResponseList.isEmpty()) {
            final int size = this.coinResponseList.size();
            coinResponseList.addAll(additionCoinResponseList);
            notifyItemRangeChanged(size, additionCoinResponseList.size());
        }
    }

    public void swapItems(List<CoinResponse> newCoinResponseList) {
        if (this.coinResponseList == null) {
            this.coinResponseList = new ArrayList<>();
        } else if (!this.coinResponseList.isEmpty()) {
            this.coinResponseList.clear();
        }
        if (newCoinResponseList != null && !newCoinResponseList.isEmpty()) {
            coinResponseList.addAll(newCoinResponseList);
        }
        this.notifyDataSetChanged();
    }

    public void unbind() {
        unbinder.unbind();
    }

    public class CoinViewHolder extends WearableRecyclerView.ViewHolder {
        @BindView(R.id.text_price)
        TextView priceText;

        @BindView(R.id.text_percent)
        TextView percentText;

        public CoinViewHolder(View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
        }
    }
}
