package org.betterx.wover.core.mixin.registry;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import net.minecraft.core.Holder;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.RegistrationInfo;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import org.betterx.wover.common.registry.api.CustomRegistryData;
import org.betterx.wover.core.impl.registry.DatapackLoadElementImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MappedRegistry.class)
public class MappedRegistryMixin implements CustomRegistryData {

    private final Map<Identifier, Object> wover$custom = new HashMap<>();

    public <T> @Nullable T wover_getData(@NotNull DataKey<T> id) {
        return (T) wover$custom.get(id.id);
    }

    public <T> @Nullable T wover_computeDataIfAbsent(
        @NotNull DataKey<T> id,
        @NotNull Function<Identifier, T> fkt
    ) {
        return (T) wover$custom.computeIfAbsent(id.id, fkt);
    }

    public <T> void wover_putData(@NotNull DataKey<T> id, @Nullable T data) {
        wover$custom.put(id.id, data);
    }

    @Inject(method = "register", at = @At("HEAD"), cancellable = false)
    public <T> void wover_register(
        ResourceKey<T> resourceKey,
        T value,
        RegistrationInfo registrationInfo,
        CallbackInfoReturnable<Holder.Reference<T>> cir
    ) {
        if (value != null) {
            DatapackLoadElementImpl.didLoadFromDatapack(resourceKey, value);
        }
    }
}
