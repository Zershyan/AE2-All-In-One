package com.linearpast.ae2allinone.registry;

import appeng.api.storage.StorageCells;
import com.linearpast.ae2allinone.cells.AIOCellHandler;
import com.linearpast.ae2allinone.cells.AIOFluidCellHandler;
import com.linearpast.ae2allinone.cells.AIOFluidStorageCell;
import com.linearpast.ae2allinone.cells.AIOStorageCell;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class CommonEvents {
    public static void setup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            AIOStorageCell.loadAllItems();
            AIOFluidStorageCell.loadAllFluids();

            StorageCells.addCellHandler(AIOCellHandler.INSTANCE);
            StorageCells.addCellHandler(AIOFluidCellHandler.INSTANCE);
        });
    }
}
