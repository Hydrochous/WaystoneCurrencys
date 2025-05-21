package org.hiedacamellia.wscurrencys.content.waystone;

import io.github.lightman314.lightmanscurrency.api.money.value.MoneyValue;
import io.github.lightman314.lightmanscurrency.api.money.value.builtin.CoinValue;
import net.blay09.mods.waystones.api.requirement.*;
import net.blay09.mods.waystones.requirement.RequirementRegistry;
import net.minecraft.resources.ResourceLocation;
import org.hiedacamellia.wscurrencys.WaystoneCurrency;
import org.hiedacamellia.wscurrencys.core.config.WSCCommonConfig;

import java.util.function.Supplier;

public class CurrencyRequirementFunction  {

    public static RequirementFunction<CurrencyWarpRequirement, RequirementRegistry.VariableScaledParameter> scaled_add_currency = modifier(
            "scaled_add_currency_cost",
            new CurrencyWarpRequirementType(),
            RequirementRegistry.VariableScaledParameter.class,
            (requirement, context, parameters) -> {
                float sourceValue = context.getContextValue(parameters.id().value());
                requirement.setValue(CoinValue.fromNumber("main", (long) (sourceValue*parameters.scale().value())));
                return requirement;
            },
            WSCCommonConfig.EnableCurrencyConsumption
    );

    public static RequirementFunction<CurrencyWarpRequirement,CurrencyParameter> max_currency = modifier(
            "max_currency_cost",
            new CurrencyWarpRequirementType(),
            CurrencyParameter.class,
            (requirement, context, parameters) -> {
                MoneyValue value = requirement.getValue();
                if (value.containsValue(parameters.value())) {
                    requirement.setValue(value.fromCoreValue(parameters.value().getCoreValue()));
                }
                WaystoneCurrency.LOGGER.debug("max_currency_cost: {} > {} = {}", value.getCoreValue(), parameters.value().getCoreValue(), requirement.getValue().getCoreValue());
                return requirement;
            },
            WSCCommonConfig.EnableCurrencyConsumption
    );

    public static RequirementFunction<CurrencyWarpRequirement,CurrencyParameter> min_currency = modifier(
            "min_currency_cost",
            new CurrencyWarpRequirementType(),
            CurrencyParameter.class,
            (requirement, context, parameters) -> {
                MoneyValue value = requirement.getValue();
                if (!value.containsValue(parameters.value())) {
                    requirement.setValue(value.fromCoreValue(parameters.value().getCoreValue()));
                }
                WaystoneCurrency.LOGGER.debug("min_currency_cost: {} - {} = {}", value.getCoreValue(), parameters.value().getCoreValue(), requirement.getValue().getCoreValue());
                return requirement;
            },
            WSCCommonConfig.EnableCurrencyConsumption
    );

    public static RequirementFunction<CurrencyWarpRequirement,CurrencyParameter> add_currency = modifier(
            "add_currency_cost",
            new CurrencyWarpRequirementType(),
            CurrencyParameter.class,
            (requirement, context, parameters) -> {
                MoneyValue value = requirement.getValue();
                long l = value.getCoreValue() + parameters.value().getCoreValue();
                requirement.setValue(CoinValue.fromNumber("main", l));
                WaystoneCurrency.LOGGER.debug("add_currency_cost: {} + {} = {}", value.getCoreValue(), parameters.value().getCoreValue(), requirement.getValue().getCoreValue());

                return requirement;
            },
            WSCCommonConfig.EnableCurrencyConsumption
    );


    private static <T extends WarpRequirement, P> RequirementFunction<T, P> modifier(final String name, final RequirementType<T> requirementType, final Class<P> parameterType, final WarpRequirementModifierFunction<T, P> function, final Supplier<Boolean> predicate) {
        return new RequirementFunction<>() {
            public ResourceLocation getId() {
                return WaystoneCurrency.rl(name);
            }

            public ResourceLocation getRequirementType() {
                return requirementType.getId();
            }

            public Class<P> getParameterType() {
                return parameterType;
            }

            public T apply(T requirement, WarpRequirementsContext context, P parameters) {
                return function.apply(requirement, context, parameters);
            }

            public boolean isEnabled() {
                return (Boolean) predicate.get();
            }
        };
    }
}
