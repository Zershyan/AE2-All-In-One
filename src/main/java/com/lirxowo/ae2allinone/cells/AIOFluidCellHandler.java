package com.lirxowo.ae2allinone.cells;

import appeng.api.storage.cells.ICellHandler;
import appeng.api.storage.cells.ISaveProvider;
import com.lirxowo.ae2allinone.item.AllFluidCell;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AIOFluidCellHandler implements ICellHandler {
    public static final AIOFluidCellHandler INSTANCE = new AIOFluidCellHandler();

    @Override
    public boolean isCell(ItemStack is) {
        return is != null && is.getItem() instanceof AllFluidCell;
    }

    @Override
    @Nullable
    public AIOFluidStorageCell getCellInventory(ItemStack is, @Nullable ISaveProvider container) {
        return AIOFluidStorageCell.createInventory(is);
    }

    public void addCellInformationToTooltip(ItemStack is, List<Component> lines) {
        var handler = getCellInventory(is, null);
        if (handler == null) return;
        lines.add(Component.translatable("tooltip.ae2allinone.all_fluid_cell"));
    }
}
