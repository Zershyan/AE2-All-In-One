package com.linearpast.ae2allinone.cells;

import appeng.api.config.Actionable;
import appeng.api.networking.security.IActionSource;
import appeng.api.stacks.AEItemKey;
import appeng.api.stacks.AEKey;
import appeng.api.stacks.KeyCounter;
import appeng.api.storage.cells.CellState;
import appeng.api.storage.cells.StorageCell;
import com.linearpast.ae2allinone.item.AllFluidCell;
import com.linearpast.ae2allinone.item.AllItemCell;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.GameMasterBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class AIOStorageCell implements StorageCell {
    private static final Set<ItemStack> items = new LinkedHashSet<>();
    private Object2LongMap<AEKey> storedAmounts;

    public AIOStorageCell() {

    }

    public static AIOStorageCell createInventory(ItemStack o) {
        Objects.requireNonNull(o, "Cannot create cell inventory for null itemstack");

        if (!(o.getItem() instanceof AllItemCell)) {
            return null;
        }

        return new AIOStorageCell();
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

    public static void loadAllItems() {
        items.clear();
        ForgeRegistries.ITEMS.getValues().forEach(item -> {
            try {
                assert !(item instanceof GameMasterBlockItem);
                assert !(item instanceof AllItemCell);
                assert !(item instanceof AllFluidCell);
                assert !(item instanceof SpawnEggItem);
                ItemStack stack = item.getDefaultInstance();
                items.add(stack);
            } catch (Exception | AssertionError ignored) {}
        });
    }

    private void loadCellItems() {
        items.stream().map(AEItemKey::of).forEach(aeItemKey -> {
            if(aeItemKey != null) {
                storedAmounts.put(aeItemKey, Integer.MAX_VALUE);
            }
        });
    }

    protected Object2LongMap<AEKey> getCellItems() {
        if (this.storedAmounts == null) {
            this.storedAmounts = new Object2LongOpenHashMap<>();
            this.loadCellItems();
        }

        return this.storedAmounts;
    }

    @Override
    public void getAvailableStacks(KeyCounter out) {
        for (var entry : this.getCellItems().object2LongEntrySet()) {
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
