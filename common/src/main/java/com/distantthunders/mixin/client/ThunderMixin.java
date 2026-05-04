package com.distantthunders.mixin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;
import java.util.logging.Logger;

@Mixin(LightningBolt.class)
public class ThunderMixin {

    @Unique
    private boolean distantthunders$firstTick = true;


    @Unique
    protected final RandomSource random_new = RandomSource.create();

    @Inject(method = "tick", at = @At("HEAD"))
    private void changeInitialLife(CallbackInfo ci) {
        if (distantthunders$firstTick) {
            LightningBolt bolt = (LightningBolt) (Object) this;
            LocalPlayer player = Minecraft.getInstance().player;

            double distanceToEntity = Objects.requireNonNull(player).distanceTo(bolt);

            ((LightningBoltAccessor) bolt).setLife(Math.max(distantthunders$getDelayTicks(distanceToEntity), 2));

            distantthunders$firstTick = false;
        }
    }

    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;playLocalSound(DDDLnet/minecraft/sounds/SoundEvent;Lnet/minecraft/sounds/SoundSource;FFZ)V"))
    private void playSound(Level level, double x, double y, double z, SoundEvent sound, SoundSource source, float volume, float pitch, boolean useDistance) {
        LightningBolt bolt = (LightningBolt) (Object) this;
        LocalPlayer player = Minecraft.getInstance().player;

        double distanceToEntity = Objects.requireNonNull(player).distanceTo(bolt);


        float maxDistance = 128;
        float pitchBonus = (float) distantthunders$clamp(1.0 - (Math.min(distanceToEntity, maxDistance) / maxDistance), 0.1F, 1F);

        level
                .playLocalSound(
                        x, y, z, SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.WEATHER, distantthunders$getSoundVolume(75, distanceToEntity), 0.8F * pitchBonus + this.random_new.nextFloat() * 0.2F, false
                );
        level
                .playLocalSound(
                        x, y, z, SoundEvents.LIGHTNING_BOLT_IMPACT, SoundSource.WEATHER, distantthunders$getSoundVolume(2.0F, distanceToEntity), 0.5F + this.random_new.nextFloat() * 0.2F, false
                );
    }

    @Unique
    private static int distantthunders$getDelayTicks(double distance) {
        return (int) distance / 128;
    }

    @Unique
    private static float distantthunders$getSoundVolume(float baseVolume, double distance) {
        float maxDistance = 128;

        return (float) (baseVolume * Math.max(1.0 - (Math.min(distance, maxDistance) / maxDistance), 0.24));
    }


    int distantthunders$clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
}
