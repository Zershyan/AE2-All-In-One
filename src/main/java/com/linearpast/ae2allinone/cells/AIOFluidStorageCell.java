package com.linearpast.ae2allinone.cells;

import appeng.api.config.Actionable;
import appeng.api.networking.security.IActionSource;
import appeng.api.stacks.AEFluidKey;
import appeng.api.stacks.AEKey;
import appeng.api.stacks.KeyCounter;
import appeng.api.storage.cells.CellState;
import appeng.api.storage.cells.StorageCell;
import com.linearpast.ae2allinone.item.AllFluidCell;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class AIOFluidStorageCell implements StorageCell {
    private static final Set<Fluid> fluids = new LinkedHashSet<>();
    private Object2LongMap<AEKey> storedAmounts;

    public AIOFluidStorageCell() {
    }

    public static AIOFluidStorageCell createInventory(ItemStack o) {
        Objects.requireNonNull(o, "Cannot create cell inventory for null itemstack");

        if (!(o.getItem() instanceof AllFluidCell)) {
            return null;
        }

        return new AIOFluidStorageCell();
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

    public static void loadAllFluids() {
        fluids.clear();
        ForgeRegistries.FLUIDS.getValues().forEach(fluid -> {
            try {
                if (fluid == Fluids.EMPTY) {
                    return;
                }
                if (fluid.isSource(fluid.defaultFluidState())) {
                    fluids.add(fluid);
                }
            } catch (Exception ignored) {}
        });
    }

    private void loadCellFluids() {
        fluids.forEach(fluid -> {
            AEFluidKey aeFluidKey = AEFluidKey.of(fluid, null);
            if (aeFluidKey != null) {
                storedAmounts.put(aeFluidKey, Integer.MAX_VALUE * 1000L);
            }
        });
    }

    protected Object2LongMap<AEKey> getCellFluids() {
        if (this.storedAmounts == null) {
            this.storedAmounts = new Object2LongOpenHashMap<>();
            this.loadCellFluids();
        }

        return this.storedAmounts;
    }

    @Override
    public void getAvailableStacks(KeyCounter out) {
        for (var entry : this.getCellFluids().object2LongEntrySet()) {
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
