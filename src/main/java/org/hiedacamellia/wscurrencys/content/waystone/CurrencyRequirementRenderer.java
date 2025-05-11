package org.hiedacamellia.wscurrencys.content.waystone;

import io.github.lightman314.lightmanscurrency.api.money.value.MoneyValue;
import net.blay09.mods.waystones.client.requirement.RequirementRenderer;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class CurrencyRequirementRenderer implements RequirementRenderer<CurrencyWarpRequirement> {

    private static final ResourceLocation COIN_GOLD = ResourceLocation.fromNamespaceAndPath("lightmanscurrency", "textures/item/coin_gold.png");

    @Override
    public void renderWidget(Player player, CurrencyWarpRequirement currencyWarpRequirement,
                             GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks, int x, int y) {

        MoneyValue value = currencyWarpRequirement.getValue();
        if(value.isFree()||value.isEmpty())return;

        //WaystoneCurrency.LOGGER.debug("Render {} at {},{}", value, x, y);
        //MutableComponent moneyRequirementText = Component.literal(String.valueOf(value.getCoreValue()));
        MutableComponent moneyRequirementText = Component.literal(String.valueOf(value.getCoreValue()));
        moneyRequirementText.withStyle(currencyWarpRequirement.canAfford(player) ? ChatFormatting.GREEN : ChatFormatting.RED);

        guiGraphics.blit(COIN_GOLD, x, y, 16, 16, 16, 16, 16, 16);
        Font font = Minecraft.getInstance().font;
        guiGraphics.drawString(font,moneyRequirementText, x + 18, y+font.lineHeight/2, 0xFFFFFFFF, false);

    }
}
