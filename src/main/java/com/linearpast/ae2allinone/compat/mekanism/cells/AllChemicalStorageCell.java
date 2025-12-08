package com.linearpast.ae2allinone.compat.mekanism.cells;

import appeng.api.config.Actionable;
import appeng.api.networking.security.IActionSource;
import appeng.api.stacks.AEKey;
import appeng.api.stacks.KeyCounter;
import appeng.api.storage.cells.CellState;
import appeng.api.storage.cells.StorageCell;
import com.linearpast.ae2allinone.compat.mekanism.item.AllChemicalCell;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import me.ramidzkh.mekae2.ae2.MekanismKey;
import mekanism.api.MekanismAPI;
import mekanism.api.chemical.ChemicalStack;
import mekanism.api.chemical.gas.GasStack;
import mekanism.api.chemical.infuse.InfusionStack;
import mekanism.api.chemical.pigment.PigmentStack;
import mekanism.api.chemical.slurry.SlurryStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AllChemicalStorageCell implements StorageCell {
    private static final List<ChemicalStack<?>> chemicals = new ArrayList<>();
    private Object2LongMap<AEKey> storedAmounts;

    public AllChemicalStorageCell() {
    }

    public static AllChemicalStorageCell createInventory(ItemStack o) {
        Objects.requireNonNull(o, "Cannot create cell inventory for null itemstack");

        if (!(o.getItem() instanceof AllChemicalCell)) {
            return null;
        }

        return new AllChemicalStorageCell();
    }

    @Override
    public CellState getStatus() {
        return CellState.FULL;
    }

    @Override
    public double getIdleDrain() {
        return 0;
    }

    @Override
    public void persist() {
    }

    @Override
    public Component getDescription() {
        return Component.empty();
    }

    public static void loadAllChemicals() {
        chemicals.clear();
        try {
            MekanismAPI.gasRegistry().getValues().forEach(gas -> {
                try {
                    if (!gas.isEmptyType()) {
                        chemicals.add(new GasStack(gas, 1));
                    }
                } catch (Exception ignored) {}
            });
            MekanismAPI.infuseTypeRegistry().getValues().forEach(infuseType -> {
                try {
                    if (!infuseType.isEmptyType()) {
                        chemicals.add(new InfusionStack(infuseType, 1));
                    }
                } catch (Exception ignored) {}
            });
            MekanismAPI.pigmentRegistry().getValues().forEach(pigment -> {
                try {
                    if (!pigment.isEmptyType()) {
                        chemicals.add(new PigmentStack(pigment, 1));
                    }
                } catch (Exception ignored) {}
            });

            MekanismAPI.slurryRegistry().getValues().forEach(slurry -> {
                try {
                    if (!slurry.isEmptyType()) {
                        chemicals.add(new SlurryStack(slurry, 1));
                    }
                } catch (Exception ignored) {}
            });
        } catch (Exception ignored) {}
    }

    private void loadCellChemicals() {
        chemicals.forEach(chemicalStack -> {
            try {
                AEKey key = MekanismKey.of(chemicalStack);
                if (key != null) {
                    storedAmounts.put(key, Integer.MAX_VALUE * 1000L);
                }
            } catch (Exception ignored) {}
        });
    }

    protected Object2LongMap<AEKey> getCellChemicals() {
        if (this.storedAmounts == null) {
            this.storedAmounts = new Object2LongOpenHashMap<>();
            this.loadCellChemicals();
        }

        return this.storedAmounts;
    }

    @Override
    public void getAvailableStacks(KeyCounter out) {
        for (var entry : this.getCellChemicals().object2LongEntrySet()) {
            try {
                out.add(entry.getKey(), entry.getLongValue());
            } catch (Exception ignored) {}
        }
    }

    @Override
    public long extract(AEKey what, long amount, Actionable mode, IActionSource source) {
        return amount;
    }

    @Override
    public boolean isPreferredStorageFor(AEKey what, IActionSource source) {
        return false;
    }
}
