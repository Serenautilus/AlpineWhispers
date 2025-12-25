package net.satisfy.alpinewhispers.neoforge.core.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class AlpineWhispersClientConfig {

    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.BooleanValue snowFogEnabled;
    public static final ModConfigSpec.DoubleValue snowFogStrength;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        snowFogEnabled = builder
                .define("snowFogEnabled", true);

        snowFogStrength = builder
                .defineInRange("snowFogStrength", 1.0D, 0.0D, 2.0D);

        SPEC = builder.build();
    }

    private AlpineWhispersClientConfig() {
    }
}