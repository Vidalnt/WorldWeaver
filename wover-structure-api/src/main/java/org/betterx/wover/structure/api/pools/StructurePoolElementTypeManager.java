package org.betterx.wover.structure.api.pools;

import com.mojang.serialization.MapCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElementType;
import org.betterx.wover.structure.impl.pools.StructurePoolElementTypeManagerImpl;

public class StructurePoolElementTypeManager {

    public static <P extends StructurePoolElement> StructurePoolElementType<
        P
    > register(Identifier location, MapCodec<P> codec) {
        return StructurePoolElementTypeManagerImpl.register(location, codec);
    }
}
