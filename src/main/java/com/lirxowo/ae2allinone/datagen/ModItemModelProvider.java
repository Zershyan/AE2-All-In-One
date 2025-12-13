package com.lirxowo.ae2allinone.datagen;

import com.lirxowo.ae2allinone.Ae2allinone;
import com.lirxowo.ae2allinone.compat.mekanism.MekanismItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ModItemModelProvider extends ItemModelProvider {

    public static final String GENERATED = "item/generated";

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Ae2allinone.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        Set<DeferredHolder<Item, ? extends Item>> items = new HashSet<>(Ae2allinone.ITEMS.getEntries());
        if (MekanismItems.isRegistered()) {
            items.addAll(MekanismItems.ITEMS.getEntries());
        }
        items.forEach(item -> itemGenerateModel(
                item.get(), resourceItem(item)
        ));
    }

    public void itemGenerateModel(Item item, ResourceLocation location) {
        withExistingParent(itemName(item), GENERATED).texture("layer0", location);
    }

    public String itemName(Item item) {
        return Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item)).getPath();
    }

    public ResourceLocation resourceItem(DeferredHolder<Item, ? extends Item> item) {
        String prefix = "item/";
        if (item.getId() != null) {
            return ResourceLocation.fromNamespaceAndPath(Ae2allinone.MODID, prefix + item.getId().getPath());
        } else {
            throw new IllegalArgumentException("Unknown item id.");
        }
    }
}
