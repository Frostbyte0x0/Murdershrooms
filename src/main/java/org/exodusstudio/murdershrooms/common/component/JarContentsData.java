package org.exodusstudio.murdershrooms.common.component;

import com.google.common.collect.Iterables;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ARGB;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import org.exodusstudio.murdershrooms.common.item.custom.alchemy.Jar;
import org.exodusstudio.murdershrooms.common.registry.DataComponentTypeRegistry;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import static org.exodusstudio.murdershrooms.common.item.custom.alchemy.Jars.jarFromEffect;

public record JarContentsData(Optional<Holder<Jar>> jar, Optional<Integer> customColor, List<MobEffectInstance> customEffects, Optional<String> customName) {
    public static final JarContentsData EMPTY = new JarContentsData(Optional.empty(), Optional.empty(), List.of(), Optional.empty());
    private static final Component NO_EFFECT;
    public static final int BASE_JAR_COLOR = -13083194;
    private static final Codec<JarContentsData> FULL_CODEC;
    public static final Codec<JarContentsData> CODEC;
    public static final StreamCodec<RegistryFriendlyByteBuf, JarContentsData> STREAM_CODEC;

    public JarContentsData(@NotNull Holder<Jar> jar) {
        this(Optional.of(jar), jar.value().getEffects().isEmpty() ? Optional.empty() : Optional.of(jar.value().getEffects().getFirst().getEffect().value().getColor()),
                jar.value().getEffects(), Optional.empty());
    }

    public JarContentsData(PotionContents potionContents) {
        this(jarFromEffect(potionContents.customEffects().getFirst().getEffect()),
                potionContents.customColor(),
                potionContents.customEffects(),
                potionContents.customName());
    }


    public static ItemStack createItemStack(Item item, Holder<Jar> jar) {
        ItemStack itemstack = new ItemStack(item);
        itemstack.set(DataComponentTypeRegistry.JAR_CONTENTS, new JarContentsData(jar));
        itemstack.set(DataComponentTypeRegistry.FILL_LEVEL.get(), new FillLevelData(4));
        return itemstack;
    }

    public Iterable<MobEffectInstance> getAllEffects() {
        return this.jar.map(jarHolder -> (this.customEffects.isEmpty() ? ((Potion) ((Holder<?>) jarHolder).value()).getEffects() : Iterables.concat(((Potion) ((Holder<?>) this.jar.get()).value()).getEffects(), this.customEffects))).orElse(this.customEffects);
    }

    public Optional<Holder<Jar>> jar() {
        return this.jar;
    }

    public Optional<Integer> customColor() {
        return this.customColor;
    }

    public Optional<String> customName() {
        return this.customName;
    }

    public int getColor() {
        return this.getColorOr(-13083194);
    }

    public int getColorOr(int defaultValue) {
        return this.customColor.orElseGet(() -> getColorOptional(this.getAllEffects()).orElse(defaultValue));
    }

    public static OptionalInt getColorOptional(Iterable<MobEffectInstance> effects) {
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;

        for (MobEffectInstance mobeffectinstance : effects) {
            if (mobeffectinstance.isVisible()) {
                int i1 = mobeffectinstance.getEffect().value().getColor();
                int j1 = mobeffectinstance.getAmplifier() + 1;
                i += j1 * ARGB.red(i1);
                j += j1 * ARGB.green(i1);
                k += j1 * ARGB.blue(i1);
                l += j1;
            }
        }

        return l == 0 ? OptionalInt.empty() : OptionalInt.of(ARGB.color(i / l, j / l, k / l));
    }

    public Component getName(String name) {
        String s = this.customName.or(() -> this.jar.map((p_372776_) -> p_372776_.value().name())).orElse("empty");
        return Component.translatable(name + s);
    }

    public boolean hasEffects() {
        return !this.customEffects.isEmpty() || this.jar.isPresent() && !((Jar) ((Holder<?>) this.jar.get()).value()).getEffects().isEmpty();
    }

    static {
        NO_EFFECT = Component.translatable("effect.none").withStyle(ChatFormatting.GRAY);
        FULL_CODEC = RecordCodecBuilder.create((p_372777_) -> p_372777_.group(Jar.CODEC.optionalFieldOf("jar").forGetter(JarContentsData::jar), Codec.INT.optionalFieldOf("custom_color").forGetter(JarContentsData::customColor), MobEffectInstance.CODEC.listOf().optionalFieldOf("custom_effects", List.of()).forGetter(JarContentsData::customEffects), Codec.STRING.optionalFieldOf("custom_name").forGetter(JarContentsData::customName)).apply(p_372777_, JarContentsData::new));
        CODEC = Codec.withAlternative(FULL_CODEC, Jar.CODEC, JarContentsData::new);
        STREAM_CODEC = StreamCodec.composite(Jar.STREAM_CODEC.apply(ByteBufCodecs::optional), JarContentsData::jar, ByteBufCodecs.INT.apply(ByteBufCodecs::optional), JarContentsData::customColor, MobEffectInstance.STREAM_CODEC.apply(ByteBufCodecs.list()), JarContentsData::customEffects, ByteBufCodecs.STRING_UTF8.apply(ByteBufCodecs::optional), JarContentsData::customName, JarContentsData::new);
    }
}
