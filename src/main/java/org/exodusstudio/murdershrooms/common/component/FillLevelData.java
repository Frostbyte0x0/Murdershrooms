package org.exodusstudio.murdershrooms.common.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

import java.io.Serializable;

public record FillLevelData(int level) implements Serializable {
    public static final Codec<FillLevelData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(Codec.INT.fieldOf("level").forGetter(FillLevelData::level)).apply(instance, FillLevelData::new));

    public static final StreamCodec<ByteBuf, FillLevelData> STREAM_CODEC = StreamCodec.composite(ByteBufCodecs.INT, FillLevelData::level, FillLevelData::new);
}
