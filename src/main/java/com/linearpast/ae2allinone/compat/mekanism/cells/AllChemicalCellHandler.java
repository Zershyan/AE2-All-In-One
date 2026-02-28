package com.linearpast.ae2allinone.compat.mekanism.cells;

import appeng.api.storage.cells.ICellHandler;
import appeng.api.storage.cells.ISaveProvider;
import com.linearpast.ae2allinone.compat.mekanism.item.AllChemicalCell;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class AllChemicalCellHandler implements ICellHandler {
    public static final AllChemicalCellHandler INSTANCE = new AllChemicalCellHandler();

    @Override
    public boolean isCell(ItemStack is) {
        return is != null && is.getItem() instanceof AllChemicalCell;
    }

    @Override
    public AllChemicalStorageCell getCellInventory(ItemStack is, ISaveProvider container) {
        return AllChemicalStorageCell.createInventory(is);
    }

    public void addCellInformationToTooltip(ItemStack is, List<Component> lines) {
        var handler = getCellInventory(is, null);
        if (handler == null) return;
        lines.add(Component.translatable("tooltip.ae2allinone.all_chemical_cell"));
    }
}
