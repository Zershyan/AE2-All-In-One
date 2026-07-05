package com.linearpast.ae2allinone.config;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class AIOConfig {
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> ITEM_BLACKLIST;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> FLUID_BLACKLIST;
    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> CHEMICAL_BLACKLIST;

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment(
                "AE2 All-In-One blacklist configuration.",
                "Entry format: exact id (e.g. minecraft:stone) or tag prefixed with # (e.g. #forge:ores).",
                "A game restart is required for changes to take effect."
        );

        builder.push("items");
        ITEM_BLACKLIST = builder
                .comment("Items excluded from the All-Item storage cell.")
                .defineList(List.of("blacklist"), List.of(), obj -> obj instanceof String);
        builder.pop();

        builder.push("fluids");
        FLUID_BLACKLIST = builder
                .comment("Fluids excluded from the All-Fluid storage cell.")
                .defineList(List.of("blacklist"), List.of(), obj -> obj instanceof String);
        builder.pop();

        builder.push("chemicals");
        CHEMICAL_BLACKLIST = builder
                .comment("Chemicals excluded from the All-Chemical storage cell (requires Mekanism).")
                .defineList(List.of("blacklist"), List.of(), obj -> obj instanceof String);
        builder.pop();

        SPEC = builder.build();
    }
}
