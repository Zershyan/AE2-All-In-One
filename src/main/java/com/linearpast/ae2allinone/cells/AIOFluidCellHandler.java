package com.linearpast.ae2allinone.cells;

import appeng.api.storage.cells.ICellHandler;
import appeng.api.storage.cells.ISaveProvider;
import com.linearpast.ae2allinone.item.AllFluidCell;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class AIOFluidCellHandler implements ICellHandler {
    public static final AIOFluidCellHandler INSTANCE = new AIOFluidCellHandler();

    @Override
    public boolean isCell(ItemStack is) {
        return is != null && is.getItem() instanceof AllFluidCell;
    }

    @Override
    public AIOFluidStorageCell getCellInventory(ItemStack is, ISaveProvider container) {
        return AIOFluidStorageCell.createInventory(is);
    }

    public void addCellInformationToTooltip(ItemStack is, List<Component> lines) {
        var handler = getCellInventory(is, null);
        if (handler == null) return;
        lines.add(Component.translatable("tooltip.ae2allinone.all_fluid_cell"));
    }
}
