package com.linearpast.ae2allinone;

import com.linearpast.ae2allinone.item.AllItemCell;
import com.linearpast.ae2allinone.registry.CommonEvents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Ae2AllInOne.MODID)
public class Ae2AllInOne {
    public static final String MODID = "ae2allinone";
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final RegistryObject<Item> ALL_ITEM_CELL = ITEMS.register("all_item_cell", () ->
            new AllItemCell(new Item.Properties().stacksTo(1)));
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final RegistryObject<CreativeModeTab> AE2_ALL_IN_ONE_TAB = CREATIVE_MODE_TABS.register("ae2allinone_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + MODID))
                    .icon(() -> new ItemStack(ALL_ITEM_CELL.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ALL_ITEM_CELL.get());
                    })
                    .build());

    public Ae2AllInOne() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        ITEMS.register(modBus);
        CREATIVE_MODE_TABS.register(modBus);

        modBus.addListener(CommonEvents::setup);
    }
}
