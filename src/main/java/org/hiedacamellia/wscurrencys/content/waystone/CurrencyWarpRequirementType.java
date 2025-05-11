package org.hiedacamellia.wscurrencys.content.waystone;

import net.blay09.mods.waystones.api.requirement.RequirementType;
import net.minecraft.resources.ResourceLocation;
import org.hiedacamellia.wscurrencys.WaystoneCurrency;

public class CurrencyWarpRequirementType implements RequirementType<CurrencyWarpRequirement> {

    public static final ResourceLocation ID = WaystoneCurrency.rl("currency");
    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public CurrencyWarpRequirement createInstance() {
        return new CurrencyWarpRequirement(0);
    }
}
