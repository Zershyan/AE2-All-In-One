package com.lirxowo.ae2allinone.registry;

import appeng.api.storage.StorageCells;
import com.lirxowo.ae2allinone.cells.AIOCellHandler;
import com.lirxowo.ae2allinone.cells.AIOFluidCellHandler;
import com.lirxowo.ae2allinone.compat.mekanism.MekanismCompat;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommonEvents {
    public static void setup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            StorageCells.addCellHandler(AIOCellHandler.INSTANCE);
            StorageCells.addCellHandler(AIOFluidCellHandler.INSTANCE);
            MekanismCompat.init();
        });
    }
}
