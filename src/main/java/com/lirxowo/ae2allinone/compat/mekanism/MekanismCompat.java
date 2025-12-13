package com.lirxowo.ae2allinone.compat.mekanism;

import appeng.api.storage.StorageCells;
import com.lirxowo.ae2allinone.compat.mekanism.cells.AllChemicalCellHandler;
import com.lirxowo.ae2allinone.compat.mekanism.cells.AllChemicalStorageCell;
import net.neoforged.fml.ModList;

public class MekanismCompat {

    private static boolean initialized = false;

    public static boolean isMekanismLoaded() {
        return ModList.get().isLoaded("mekanism");
    }

    public static boolean isAppliedMekanisticsLoaded() {
        return ModList.get().isLoaded("appmek");
    }

    public static boolean isCompatAvailable() {
        return isMekanismLoaded() && isAppliedMekanisticsLoaded();
    }

    public static void init() {
        if (initialized) {
            return;
        }

        if (!isCompatAvailable()) {
            return;
        }

        try {
            AllChemicalStorageCell.loadAllChemicals();

            StorageCells.addCellHandler(AllChemicalCellHandler.INSTANCE);

            initialized = true;
        } catch (Exception e) {
        }
    }
}
