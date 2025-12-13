package com.lirxowo.ae2allinone;

import com.lirxowo.ae2allinone.compat.mekanism.MekanismItems;
import com.lirxowo.ae2allinone.item.AllFluidCell;
import com.lirxowo.ae2allinone.item.AllItemCell;
import com.lirxowo.ae2allinone.registry.CommonEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@Mod(Ae2allinone.MODID)
public class Ae2allinone {
    public static final String MODID = "ae2allinone";

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredItem<AllItemCell> ALL_ITEM_CELL = ITEMS.register("all_item_cell", () ->
            new AllItemCell(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<AllFluidCell> ALL_FLUID_CELL = ITEMS.register("all_fluid_cell", () ->
            new AllFluidCell(new Item.Properties().stacksTo(1)));

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final Supplier<CreativeModeTab> AE2_ALL_IN_ONE_TAB = CREATIVE_MODE_TABS.register("ae2allinone_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + MODID))
                    .icon(() -> new ItemStack(ALL_ITEM_CELL.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ALL_ITEM_CELL.get());
                        output.accept(ALL_FLUID_CELL.get());
                        if (MekanismItems.isRegistered()) {
                            if (MekanismItems.ALL_CHEMICAL_CELL != null) {
                                output.accept(MekanismItems.ALL_CHEMICAL_CELL.get());
                            }
                        }
                    })
                    .build());

    public Ae2allinone(IEventBus modBus) {
        ITEMS.register(modBus);
        CREATIVE_MODE_TABS.register(modBus);

        MekanismItems.register(modBus);

        modBus.addListener(this::setup);
    }

    private void setup(FMLCommonSetupEvent event) {
        CommonEvents.setup(event);
    }
}
