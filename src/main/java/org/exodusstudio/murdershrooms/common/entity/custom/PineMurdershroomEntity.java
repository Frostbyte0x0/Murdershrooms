package org.exodusstudio.murdershrooms.common.entity.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.exodusstudio.murdershrooms.common.registry.EffectRegistry;
import org.exodusstudio.murdershrooms.common.registry.EntityRegistry;

public class PineMurdershroomEntity extends AbstractMurdershroom {
    public PineMurdershroomEntity(EntityType<? extends Monster> entityType, Level level) {
        super(EntityRegistry.PINE_MURDERSHROOM.get(), level, EffectRegistry.PARANOIA);
    }
}
