package com.lirxowo.ae2allinone.item;

import appeng.api.config.FuzzyMode;
import appeng.api.storage.cells.ICellWorkbenchItem;
import appeng.items.AEBaseItem;
import com.google.common.base.Preconditions;
import com.lirxowo.ae2allinone.cells.AIOFluidCellHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AllFluidCell extends AEBaseItem implements ICellWorkbenchItem {
    public AllFluidCell(Item.Properties properties) {
        super(properties);
    }

    @Override
    public FuzzyMode getFuzzyMode(ItemStack itemStack) {
        return FuzzyMode.IGNORE_ALL;
    }

    @Override
    public void setFuzzyMode(ItemStack itemStack, FuzzyMode fuzzyMode) {
    }

    @Override
    public void appendHoverText(
            @NotNull ItemStack stack,
            @NotNull TooltipContext context,
            @NotNull List<Component> tooltipComponents,
            @NotNull TooltipFlag tooltipFlag
    ) {
        addCellInformationToTooltip(stack, tooltipComponents);
    }

    public void addCellInformationToTooltip(ItemStack is, List<Component> lines) {
        Preconditions.checkArgument(is.getItem() == this);
        AIOFluidCellHandler.INSTANCE.addCellInformationToTooltip(is, lines);
    }
}
