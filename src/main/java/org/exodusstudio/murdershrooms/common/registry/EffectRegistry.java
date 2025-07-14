package org.exodusstudio.murdershrooms.common.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.exodusstudio.murdershrooms.common.effect.*;
import org.exodusstudio.murdershrooms.Murdershrooms;

public class EffectRegistry {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, Murdershrooms.MOD_ID);

    public static final Holder<MobEffect> IRRITATION = MOB_EFFECTS.register("irritation",
            () -> new IrritationEffect(MobEffectCategory.HARMFUL, 0xab640e)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath(Murdershrooms.MOD_ID, "irritation_slowness"),
                            -0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.ATTACK_SPEED, ResourceLocation.fromNamespaceAndPath(Murdershrooms.MOD_ID, "irritation_attack_slowness"),
                            -0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static final Holder<MobEffect> PARANOIA = MOB_EFFECTS.register("paranoia",
            () -> new GenericEffect(MobEffectCategory.HARMFUL, 0x400b6b));

    public static final Holder<MobEffect> FATIGUE = MOB_EFFECTS.register("fatigue",
            () -> new GenericEffect(MobEffectCategory.HARMFUL, 0x3e374f));

    public static final Holder<MobEffect> TWITCHING = MOB_EFFECTS.register("twitching",
            () -> new GenericEffect(MobEffectCategory.HARMFUL, 0x2f6363));

    public static final Holder<MobEffect> CORRUPTION = MOB_EFFECTS.register("corruption",
            () -> new GenericEffect(MobEffectCategory.HARMFUL, 0xBFBC35));

    public static final Holder<MobEffect> MOLD = MOB_EFFECTS.register("mold",
            () -> new GenericEffect(MobEffectCategory.HARMFUL, 0x3F7F33));

    public static final Holder<MobEffect> DECAY = MOB_EFFECTS.register("decay",
            () -> new DecayEffect(MobEffectCategory.HARMFUL, 0x050505)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath(Murdershrooms.MOD_ID, "decay_slowness"),
                            -0.1f, AttributeModifier.Operation.ADD_MULTIPLIED_BASE));


    public static final Holder<MobEffect> RAGE = MOB_EFFECTS.register("rage",
            () -> new RageEffect(MobEffectCategory.BENEFICIAL, 0x8c1111)
                    .addAttributeModifier(Attributes.ATTACK_SPEED, ResourceLocation.fromNamespaceAndPath(Murdershrooms.MOD_ID, "rage_attack_speed"),
                            0.3f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath(Murdershrooms.MOD_ID, "rage_movement_speed"),
                            0.3f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.ATTACK_DAMAGE, ResourceLocation.fromNamespaceAndPath(Murdershrooms.MOD_ID, "rage_attack_damage"),
                            0.3f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.ARMOR, ResourceLocation.fromNamespaceAndPath(Murdershrooms.MOD_ID, "rage_armor"),
                            0.3f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

    public static boolean isSporeEffect(MobEffectInstance effectInstance) {
        return (effectInstance.is(EffectRegistry.DECAY) ||
                effectInstance.is(EffectRegistry.IRRITATION) ||
                effectInstance.is(EffectRegistry.PARANOIA) ||
                effectInstance.is(EffectRegistry.CORRUPTION) ||
                effectInstance.is(EffectRegistry.MOLD) ||
                effectInstance.is(EffectRegistry.FATIGUE) ||
                effectInstance.is(EffectRegistry.TWITCHING));
    }
}
