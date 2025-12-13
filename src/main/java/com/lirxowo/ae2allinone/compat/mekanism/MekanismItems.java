package com.lirxowo.ae2allinone.compat.mekanism;

import com.lirxowo.ae2allinone.Ae2allinone;
import com.lirxowo.ae2allinone.compat.mekanism.item.AllChemicalCell;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MekanismItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Ae2allinone.MODID);

    public static DeferredItem<AllChemicalCell> ALL_CHEMICAL_CELL;

    private static boolean registered = false;

    public static void register(IEventBus modBus) {
        if (registered) {
            return;
        }

        if (!ModList.get().isLoaded("mekanism") || !ModList.get().isLoaded("appmek")) {
            return;
        }

        ALL_CHEMICAL_CELL = ITEMS.register("all_chemical_cell", () ->
                new AllChemicalCell(new Item.Properties().stacksTo(1)));

        ITEMS.register(modBus);
        registered = true;
    }

    public static boolean isRegistered() {
        return registered;
    }
}
