package com.lirxowo.ae2allinone.config;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

public class AIOConfig {
    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> ITEM_BLACKLIST;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> FLUID_BLACKLIST;
    public static final ModConfigSpec.ConfigValue<List<? extends String>> CHEMICAL_BLACKLIST;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

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
