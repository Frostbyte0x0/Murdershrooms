package org.exodusstudio.murdershrooms.common.effect;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class IrritationEffect extends MobEffect {
    public IrritationEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
        if (entity.tickCount % (200 / (amplifier + 1)) == 0 && entity.getHealth() > 1.0f) {
            entity.hurtServer(level, entity.damageSources().magic(), 1f);
            return true;
        }
        return super.applyEffectTick(level, entity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
}
