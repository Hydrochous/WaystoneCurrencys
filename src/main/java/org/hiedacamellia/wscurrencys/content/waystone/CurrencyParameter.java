package org.hiedacamellia.wscurrencys.content.waystone;

import io.github.lightman314.lightmanscurrency.api.money.value.MoneyValue;
import io.github.lightman314.lightmanscurrency.api.money.value.builtin.CoinValue;

public record CurrencyParameter(MoneyValue value) {

    public CurrencyParameter(int value) {
        this(CoinValue.fromNumber("main", value));
    }
}
