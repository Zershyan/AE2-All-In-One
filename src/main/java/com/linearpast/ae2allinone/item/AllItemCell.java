package com.linearpast.ae2allinone.item;

import appeng.api.config.FuzzyMode;
import appeng.api.storage.cells.ICellWorkbenchItem;
import appeng.items.AEBaseItem;
import com.google.common.base.Preconditions;
import com.linearpast.ae2allinone.cells.AIOCellHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AllItemCell extends AEBaseItem implements ICellWorkbenchItem {
    public AllItemCell(Properties properties) {
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
            @NotNull ItemStack pStack,
            @Nullable Level pLevel,
            @NotNull List<Component> pTooltipComponents,
            @NotNull TooltipFlag pIsAdvanced
    ) {
        addCellInformationToTooltip(pStack, pTooltipComponents);
    }

    public void addCellInformationToTooltip(ItemStack is, List<Component> lines) {
        Preconditions.checkArgument(is.getItem() == this);
        AIOCellHandler.INSTANCE.addCellInformationToTooltip(is, lines);
    }
}
