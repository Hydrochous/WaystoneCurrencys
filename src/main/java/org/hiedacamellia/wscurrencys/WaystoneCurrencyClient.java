package org.hiedacamellia.wscurrencys;

import net.blay09.mods.waystones.api.client.WaystonesClientAPI;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.hiedacamellia.wscurrencys.content.waystone.CurrencyRequirementRenderer;
import org.hiedacamellia.wscurrencys.content.waystone.CurrencyWarpRequirement;

@Mod(value = WaystoneCurrency.MODID,dist = Dist.CLIENT)
public class WaystoneCurrencyClient
{
    public WaystoneCurrencyClient(IEventBus modEventBus, ModContainer modContainer)
    {
        WaystonesClientAPI.registerRequirementRenderer(CurrencyWarpRequirement.class,new CurrencyRequirementRenderer());
    }

}
