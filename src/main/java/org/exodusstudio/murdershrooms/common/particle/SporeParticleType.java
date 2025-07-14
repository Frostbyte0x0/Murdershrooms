package org.exodusstudio.murdershrooms.common.particle;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import org.jetbrains.annotations.NotNull;

public class SporeParticleType extends ParticleType<ColorParticleOption> implements ParticleOptions {
    public SporeParticleType(boolean overrideLimiter) {
        super(overrideLimiter);
    }

    @Override
    public MapCodec<ColorParticleOption> codec() {
        return ColorParticleOption.codec(this);
    }

    @Override
    public StreamCodec<? super RegistryFriendlyByteBuf, ColorParticleOption> streamCodec() {
        return ColorParticleOption.streamCodec(this);
    }

    @Override
    public @NotNull ParticleType<?> getType() {
        return this;
    }
}
