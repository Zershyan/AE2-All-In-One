package com.lirxowo.ae2allinone.cells;

import appeng.api.storage.cells.ICellHandler;
import appeng.api.storage.cells.ISaveProvider;
import com.lirxowo.ae2allinone.item.AllItemCell;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AIOCellHandler implements ICellHandler {
    public static final AIOCellHandler INSTANCE = new AIOCellHandler();

    @Override
    public boolean isCell(ItemStack is) {
        return is != null && is.getItem() instanceof AllItemCell;
    }

    @Override
    @Nullable
    public AIOStorageCell getCellInventory(ItemStack is, @Nullable ISaveProvider container) {
        return AIOStorageCell.createInventory(is);
    }

    public void addCellInformationToTooltip(ItemStack is, List<Component> lines) {
        var handler = getCellInventory(is, null);
        if (handler == null) return;
        lines.add(Component.translatable("tooltip.ae2allinone.all_item_cell"));
    }
}
