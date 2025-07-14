package org.exodusstudio.murdershrooms.common.event;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingChangeTargetEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.exodusstudio.murdershrooms.Murdershrooms;
import org.exodusstudio.murdershrooms.common.entity.custom.illusory.IllusoryEndermanEntity;
import org.exodusstudio.murdershrooms.common.entity.custom.illusory.IllusoryZombieEntity;
import org.exodusstudio.murdershrooms.common.item.custom.alchemy.Jars;
import org.exodusstudio.murdershrooms.common.network.PlayerHeartDataHandler;
import org.exodusstudio.murdershrooms.common.registry.DataComponentTypeRegistry;
import org.exodusstudio.murdershrooms.common.registry.EffectRegistry;
import org.exodusstudio.murdershrooms.common.registry.ItemRegistry;

import java.util.function.Supplier;

import static org.exodusstudio.murdershrooms.common.util.MathsUtil.plusOrMinus;

@EventBusSubscriber(modid = Murdershrooms.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {
    private static final RandomSource random = RandomSource.create();

    @SubscribeEvent
    public static void playerHeartEventAdded(MobEffectEvent.Added event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            if (event.getEffectInstance().is(EffectRegistry.DECAY)) {
                PacketDistributor.sendToPlayer(player, new PlayerHeartDataHandler(true));
            }
        }
    }

    @SubscribeEvent
    public static void cancelClearingEffects(MobEffectEvent.Remove event) {
        if (event.getEffectInstance() != null && EffectRegistry.isSporeEffect(event.getEffectInstance()) && event.getEntity() instanceof Player player) {
            ItemStack itemstack = player.getItemInHand(InteractionHand.MAIN_HAND);
            if (itemstack.is(ItemRegistry.JAR) &&
                    itemstack.has(DataComponentTypeRegistry.JAR_CONTENTS) &&
                    itemstack.get(DataComponentTypeRegistry.JAR_CONTENTS).jar().get().is(Jars.CURING)) {
                return;
            }
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void playerHeartEventRemoved(MobEffectEvent.Remove event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            if (event.getEffectInstance() != null && event.getEffectInstance().is(EffectRegistry.DECAY)) {
                PacketDistributor.sendToPlayer(player, new PlayerHeartDataHandler(false));
            }
        }
    }

    @SubscribeEvent
    public static void changeTargetEvent(LivingChangeTargetEvent event) {
        if (event.getEntity() instanceof IllusoryEndermanEntity || event.getEntity() instanceof IllusoryZombieEntity) {
            if (((Monster) event.getEntity()).getTarget() != null) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public static void playerTick(PlayerTickEvent.Pre event) {
        Player player = event.getEntity();

        if (player.hasEffect(EffectRegistry.PARANOIA)) {
            if (random.nextFloat() < 0.01f) {
                if (!player.level().isClientSide) {
                    ServerLevel serverlevel = (ServerLevel) player.level();

                    spawnMonsterRandomlyAroundPlayer(() -> new IllusoryZombieEntity(serverlevel), serverlevel, player, 10, 60, 5);
                }
            }
        }
    }

    public static <T extends Monster> void spawnMonsterRandomlyAroundPlayer(Supplier<T> monsterSupplier, ServerLevel serverLevel, Player player, int minDist, int maxDist, int tries) {
        for (int i = 0; i < tries; i++) {
            BlockPos blockpos = player.blockPosition().offset(
                    random.nextIntBetweenInclusive(minDist, maxDist) * plusOrMinus(),
                    random.nextIntBetweenInclusive(0, 4) * plusOrMinus(),
                    random.nextIntBetweenInclusive(minDist, maxDist) * plusOrMinus());

            if (player.level().getBlockState(blockpos).isEmpty()
                    && player.level().getBlockState(blockpos.above()).isEmpty()
                    && !player.level().getBlockState(blockpos.below()).isEmpty()) {
                T monster = monsterSupplier.get();
                monster.moveTo(blockpos, 0.0F, 0.0F);
                monster.finalizeSpawn(serverLevel, player.level().getCurrentDifficultyAt(blockpos), EntitySpawnReason.EVENT, null);
                monster.setTarget(player);

                serverLevel.addFreshEntityWithPassengers(monster);
                serverLevel.gameEvent(GameEvent.ENTITY_PLACE, blockpos, GameEvent.Context.of(player));
            }
        }
    }
}
