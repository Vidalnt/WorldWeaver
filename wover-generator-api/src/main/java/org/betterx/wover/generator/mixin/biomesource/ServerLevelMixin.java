package org.betterx.wover.generator.mixin.biomesource;

import java.util.List;
import java.util.concurrent.Executor;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.progress.LevelLoadListener; // ✅ CAMBIADO
import net.minecraft.world.RandomSequences;
import net.minecraft.world.level.CustomSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.storage.ServerLevelData;
import org.betterx.wover.common.generator.api.biomesource.BiomeSourceWithNoiseRelatedSettings;
import org.betterx.wover.common.generator.api.biomesource.BiomeSourceWithSeed;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin extends Level {

    protected ServerLevelMixin(
        MinecraftServer minecraftServer,
        Executor executor,
        LevelStorageSource.LevelStorageAccess levelStorageAccess,
        ServerLevelData serverLevelData,
        ResourceKey<Level> resourceKey,
        LevelStem levelStem,
        LevelLoadListener levelLoadListener,
        boolean bl,
        long l,
        List<CustomSpawner> list,
        boolean bl2,
        @Nullable RandomSequences randomSequences
    ) {
        super(
            serverLevelData,
            resourceKey,
            minecraftServer.registryAccess(),
            levelStem.type(),
            false,
            bl,
            l,
            minecraftServer.getMaxChainedNeighborUpdates()
        );
    }

    @Inject(method = "<init>*", at = @At("TAIL"))
    private void wover_onServerWorldInit(
        MinecraftServer minecraftServer,
        Executor executor,
        LevelStorageSource.LevelStorageAccess levelStorageAccess,
        ServerLevelData serverLevelData,
        ResourceKey resourceKey,
        LevelStem levelStem,
        LevelLoadListener levelLoadListener,
        boolean bl,
        long l,
        List list,
        boolean bl2,
        RandomSequences randomSequences,
        CallbackInfo ci
    ) {
        final ServerLevel level = ServerLevel.class.cast(this);

        if (
            levelStem.generator().getBiomeSource() instanceof
                BiomeSourceWithSeed source
        ) {
            source.setSeed(level.getSeed());
        }

        if (
            levelStem.generator().getBiomeSource() instanceof
                BiomeSourceWithNoiseRelatedSettings bcl &&
            levelStem.generator() instanceof
                NoiseBasedChunkGenerator noiseGenerator
        ) {
            bcl.onLoadGeneratorSettings(
                noiseGenerator.generatorSettings().value()
            );
        }
    }
}
