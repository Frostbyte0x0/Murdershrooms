package org.exodusstudio.murdershrooms.common.registry;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.exodusstudio.murdershrooms.common.particle.SporeParticleType;
import org.exodusstudio.murdershrooms.Murdershrooms;

import java.util.function.Supplier;

public class ParticleRegistry {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, Murdershrooms.MOD_ID);

    public static final Supplier<SporeParticleType> SPORE_PARTICLE =
            PARTICLE_TYPES.register("spore", () -> new SporeParticleType(false));
}
