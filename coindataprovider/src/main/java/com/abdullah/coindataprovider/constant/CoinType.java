package com.abdullah.coindataprovider.constant;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.abdullah.coindataprovider.constant.CoinType.BITCOIN;
import static com.abdullah.coindataprovider.constant.CoinType.ETHEREUM;

/**
 * Created by abdullah on 14.1.2018.
 */
@StringDef({BITCOIN, ETHEREUM})
@Retention(RetentionPolicy.SOURCE)
public @interface CoinType {
    String BITCOIN = "bitcoin";
    String ETHEREUM = "ethereum";
}
