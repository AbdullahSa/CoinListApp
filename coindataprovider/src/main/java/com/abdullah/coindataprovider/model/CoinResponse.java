package com.abdullah.coindataprovider.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abdullah on 13.1.2018.
 */

public class CoinResponse {
    private String id;
    private String name;
    private String symbol;
    @SerializedName("price_eur")
    private String priceEuro;
    @SerializedName("percent_change_24h")
    private String percentChangeDaily;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPriceEuro() {
        return priceEuro;
    }

    public void setPriceEuro(String priceEuro) {
        this.priceEuro = priceEuro;
    }

    public String getPercentChangeDaily() {
        return percentChangeDaily;
    }

    public void setPercentChangeDaily(String percentChangeDaily) {
        this.percentChangeDaily = percentChangeDaily;
    }

    @Override
    public String toString() {
        return "CoinResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", priceEuro='" + priceEuro + '\'' +
                ", percentChangeDaily='" + percentChangeDaily + '\'' +
                '}';
    }
}
