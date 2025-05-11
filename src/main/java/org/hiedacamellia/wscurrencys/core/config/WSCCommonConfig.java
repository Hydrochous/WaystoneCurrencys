package org.hiedacamellia.wscurrencys.core.config;

import net.neoforged.neoforge.common.ModConfigSpec;


public class WSCCommonConfig
{
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue EnableCurrencyConsumption = BUILDER
            .comment("Enable currency consumption")
            .define("EnableCurrencyConsumption", true);

    public static final ModConfigSpec SPEC = BUILDER.build();

}
