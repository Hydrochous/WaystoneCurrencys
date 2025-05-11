package org.hiedacamellia.wscurrencys.content.waystone;

import com.google.gson.JsonParser;
import io.github.lightman314.lightmanscurrency.api.money.value.MoneyValue;
import io.github.lightman314.lightmanscurrency.api.money.value.builtin.CoinValue;
import net.blay09.mods.waystones.api.requirement.ParameterSerializer;

public class CurrencyParameterSerializer implements ParameterSerializer<CurrencyParameter> {

    @Override
    public Class<CurrencyParameter> getType() {
        return CurrencyParameter.class;
    }

    @Override
    public CurrencyParameter deserialize(String s) {
        try {
            return new CurrencyParameter(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            try {
                return new CurrencyParameter(CoinValue.loadFromJson(JsonParser.parseString(s)));
            } catch (Exception e1) {
                return new CurrencyParameter(MoneyValue.free());
            }
        }
    }
}
