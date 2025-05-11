package org.hiedacamellia.wscurrencys.core.mixin;


import net.blay09.mods.waystones.requirement.RequirementModifierParser;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.regex.Pattern;

@Mixin(RequirementModifierParser.class)
public class RequirementModifierParserMixin {

    //无语了，原来的正则匹配不了冒号

    @Redirect(method = "parseRequirements(Ljava/lang/String;)Ljava/util/List;",at = @At(value = "INVOKE",target = "Ljava/util/regex/Pattern;compile(Ljava/lang/String;)Ljava/util/regex/Pattern;"))
    private static Pattern compile1(String regex) {
        return Pattern.compile("([\\w:]+)\\((.*?)\\)");
    }


    @Redirect(method = "parseRequirement(Ljava/lang/String;)Lnet/blay09/mods/waystones/requirement/ConfiguredRequirement;",at = @At(value = "INVOKE",target = "Ljava/util/regex/Pattern;compile(Ljava/lang/String;)Ljava/util/regex/Pattern;"))
    private static Pattern compile2(String regex) {
        return Pattern.compile("([\\w:]+)\\((.*?)\\)");
    }

}
