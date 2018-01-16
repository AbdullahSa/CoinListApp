package com.abdullah.coindataprovider.constant;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.abdullah.coindataprovider.constant.ExchangeType.EURO;

/**
 * Created by abdullah on 14.1.2018.
 */
@StringDef({EURO})
@Retention(RetentionPolicy.SOURCE)
public @interface ExchangeType {
    String EURO = "EUR";
    String USD = "USD";
}
