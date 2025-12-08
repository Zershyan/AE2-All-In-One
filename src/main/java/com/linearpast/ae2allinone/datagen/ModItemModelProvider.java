package com.linearpast.ae2allinone.datagen;

import com.linearpast.ae2allinone.Ae2AllInOne;
import com.linearpast.ae2allinone.compat.mekanism.MekanismItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ModItemModelProvider extends ItemModelProvider {

    public static final String GENERATED = "item/generated";

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Ae2AllInOne.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        Set<RegistryObject<Item>> items = new HashSet<>(Ae2AllInOne.ITEMS.getEntries());
        items.addAll(MekanismItems.ITEMS.getEntries());
        items.forEach(item -> itemGenerateModel(
                item.get(), resourceItem(item)
        ));
    }

    public void itemGenerateModel(Item item, ResourceLocation location){
        withExistingParent(itemName(item), GENERATED).texture("layer0", location);
    }

    public String itemName(Item item){
        return Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).getPath();
    }

    public ResourceLocation resourceItem(RegistryObject<Item> item){
        String prefix = "item/";
        if(item.getId() != null) return new ResourceLocation(Ae2AllInOne.MODID, prefix + item.getId().getPath());
        else throw new IllegalArgumentException("Unknown item id.");
    }
}
