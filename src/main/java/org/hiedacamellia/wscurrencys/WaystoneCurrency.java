package org.hiedacamellia.wscurrencys;

import com.mojang.logging.LogUtils;
import net.blay09.mods.waystones.api.WaystonesAPI;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.hiedacamellia.wscurrencys.content.waystone.CurrencyParameterSerializer;
import org.hiedacamellia.wscurrencys.content.waystone.CurrencyRequirementFunction;
import org.hiedacamellia.wscurrencys.content.waystone.CurrencyWarpRequirementType;
import org.hiedacamellia.wscurrencys.core.config.WSCCommonConfig;
import org.slf4j.Logger;

@Mod(WaystoneCurrency.MODID)
public class WaystoneCurrency
{
    public static final String MODID = "wscurrencys";
    public static final Logger LOGGER = LogUtils.getLogger();

    public WaystoneCurrency(IEventBus modEventBus, ModContainer modContainer)
    {
        WaystonesAPI.registerRequirementType(new CurrencyWarpRequirementType());
        WaystonesAPI.registerParameterSerializer(new CurrencyParameterSerializer());
        WaystonesAPI.registerRequirementModifier(CurrencyRequirementFunction.add_currency);
        WaystonesAPI.registerRequirementModifier(CurrencyRequirementFunction.max_currency);
        WaystonesAPI.registerRequirementModifier(CurrencyRequirementFunction.min_currency);
        WaystonesAPI.registerRequirementModifier(CurrencyRequirementFunction.scaled_add_currency);
        WaystonesAPI.registerRequirementModifier(CurrencyRequirementFunction.multiply_currency);


        modContainer.registerConfig(ModConfig.Type.COMMON, WSCCommonConfig.SPEC);
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
