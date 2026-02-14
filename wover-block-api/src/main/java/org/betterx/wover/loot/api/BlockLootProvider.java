package org.betterx.wover.loot.api;

import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jetbrains.annotations.NotNull;

//TODO: @Deprecated(forRemoval = true)
public interface BlockLootProvider {
    LootTable.Builder registerBlockLoot(
        @NotNull Identifier location,
        @NotNull LootLookupProvider provider,
        @NotNull ResourceKey<LootTable> tableKey
    );
}
