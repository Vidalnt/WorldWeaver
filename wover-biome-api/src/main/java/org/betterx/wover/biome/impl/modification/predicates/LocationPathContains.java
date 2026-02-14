package org.betterx.wover.biome.impl.modification.predicates;

import com.mojang.serialization.Codec;
import net.minecraft.util.KeyDispatchDataCodec;
import org.betterx.wover.biome.api.modification.predicates.BiomePredicate;

public record LocationPathContains(String needle) implements BiomePredicate {
    public static final KeyDispatchDataCodec<LocationPathContains> CODEC =
        KeyDispatchDataCodec.of(
            Codec.STRING.xmap(
                LocationPathContains::new,
                LocationPathContains::needle
            ).fieldOf("needle")
        );

    @Override
    public KeyDispatchDataCodec<? extends BiomePredicate> codec() {
        return CODEC;
    }

    @Override
    public boolean test(Context ctx) {
        return ctx.biomeKey.identifier().getPath().contains(needle);
    }
}
