package org.hiedacamellia.wscurrencys.content.waystone;

import com.mojang.blaze3d.vertex.PoseStack;
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

    private static final ResourceLocation COIN_COPPER = ResourceLocation.fromNamespaceAndPath("lightmanscurrency", "textures/item/coin_copper.png");
    private static final ResourceLocation COIN_IRON = ResourceLocation.fromNamespaceAndPath("lightmanscurrency", "textures/item/coin_iron.png");
    private static final ResourceLocation COIN_GOLD = ResourceLocation.fromNamespaceAndPath("lightmanscurrency", "textures/item/coin_gold.png");
    private static final ResourceLocation COIN_DIAMOND = ResourceLocation.fromNamespaceAndPath("lightmanscurrency", "textures/item/coin_diamond.png");
    private static final ResourceLocation COIN_EMERALD = ResourceLocation.fromNamespaceAndPath("lightmanscurrency", "textures/item/coin_emerald.png");
    private static final ResourceLocation COIN_NETHERITE = ResourceLocation.fromNamespaceAndPath("lightmanscurrency", "textures/item/coin_netherite.png");

    private static Font font(){
        return Minecraft.getInstance().font;
    }

    @Override
    public void renderWidget(Player player, CurrencyWarpRequirement currencyWarpRequirement,
                             GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks, int x, int y) {

        MoneyValue value = currencyWarpRequirement.getValue();
        if(value.isFree()||value.isEmpty())return;

        PoseStack pose = guiGraphics.pose();
        pose.pushPose();

        long coreValue = value.getCoreValue();
        long c = coreValue%10;coreValue/=10;
        long i = coreValue%10;coreValue/=10;
        long g = coreValue%10;coreValue/=10;
        long d = coreValue%10;coreValue/=10;
        long e = coreValue%10;coreValue/=10;
        long n = coreValue;

        boolean canAfford = currencyWarpRequirement.canAfford(player);

        renderCoin(guiGraphics,x,y,COIN_NETHERITE,(int)n,canAfford);
        renderCoin(guiGraphics,x,y,COIN_DIAMOND,(int)e,canAfford);
        renderCoin(guiGraphics,x,y,COIN_EMERALD,(int)d,canAfford);
        renderCoin(guiGraphics,x,y,COIN_GOLD,(int)g,canAfford);
        renderCoin(guiGraphics,x,y,COIN_IRON,(int)i,canAfford);
        renderCoin(guiGraphics,x,y,COIN_COPPER,(int)c,canAfford);



        pose.popPose();

    }

    private void renderCoin(GuiGraphics guiGraphics,int x,int y,ResourceLocation resourceLocation,int count,boolean canAfford) {
        if(count==0)return;
        int width = font().width(String.valueOf(count));
        PoseStack pose = guiGraphics.pose();
        guiGraphics.blit(resourceLocation, x, y, 16, 16, 16, 16, 16, 16);
        MutableComponent moneyRequirementText = Component.literal(String.valueOf(count));
        moneyRequirementText.withStyle(canAfford ? ChatFormatting.GREEN : ChatFormatting.RED);
        guiGraphics.drawString(font(),moneyRequirementText, x + 17, y+font().lineHeight/2, 0xFFFFFFFF, false);
        pose.translate(width+16,0,0);
    }
}
