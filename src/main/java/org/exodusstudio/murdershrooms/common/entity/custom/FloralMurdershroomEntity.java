package org.exodusstudio.murdershrooms.common.entity.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.exodusstudio.murdershrooms.common.registry.EffectRegistry;
import org.exodusstudio.murdershrooms.common.registry.EntityRegistry;

public class FloralMurdershroomEntity extends AbstractMurdershroom {
    public FloralMurdershroomEntity(EntityType<? extends Monster> entityType, Level level) {
        super(EntityRegistry.FLORAL_MURDERSHROOM.get(), level, EffectRegistry.IRRITATION);
    }
}
