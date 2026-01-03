package com.example.plugin.mixin.mixins;

import com.example.plugin.mixin.mixins.utils.SocialBubbleEngine;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;


import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.world.level.Level;

// TODO: remove this. this is only for example
@Mixin(Entity.class)
public abstract class EntityPushMixin {
    @Shadow public Level level;
    @Shadow public boolean noPhysics;
    @Shadow protected abstract boolean isVehicleOf(Entity entity);

    Entity self = (Entity)(Object)this;
    @Overwrite
    public void push(Entity entity) {
        if (!this.isVehicleOf(entity)) {
            if (!entity.noPhysics && !this.noPhysics) {
                if (this.level.paperConfig().collisions.onlyPlayersCollide && !(entity instanceof ServerPlayer || self instanceof ServerPlayer))
                    return; // Paper - Collision option for requiring a player participant
                if (SocialBubbleEngine.getInstance().shouldSkipHardCollision(self, entity)) {
                    return;
                }
            }
        }
    }
}
