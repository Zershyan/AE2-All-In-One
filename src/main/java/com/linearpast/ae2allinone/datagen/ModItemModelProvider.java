package com.linearpast.ae2allinone.datagen;

import com.linearpast.ae2allinone.Ae2AllInOne;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Ae2AllInOne.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        getBuilder("all_item_cell")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Ae2AllInOne.MODID, "item/all_item_cell"));

        getBuilder("all_fluid_cell")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", new ResourceLocation(Ae2AllInOne.MODID, "item/all_fluid_cell"));
    }
}
