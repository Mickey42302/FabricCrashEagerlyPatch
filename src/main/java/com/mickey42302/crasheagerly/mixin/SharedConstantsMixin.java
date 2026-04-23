package com.mickey42302.crasheagerly.mixin;

import com.mojang.logging.LogUtils;
import net.minecraft.SharedConstants;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import org.slf4j.Logger;

@Mixin({SharedConstants.class})
public abstract class SharedConstantsMixin {
    @Final
    @Shadow
    @Mutable
    public static boolean CRASH_ON_UNCAUGHT_THREAD_EXCEPTION;

    @Unique
    private static final Logger LOGGER = LogUtils.getLogger();

    @Inject(method = {"<clinit>"}, at = {@At("TAIL")})
    private static void crasheagerly$clinit(CallbackInfo ci) {
        CRASH_ON_UNCAUGHT_THREAD_EXCEPTION = true;
        LOGGER.warn("Warning: The Crash Eagerly feature is enabled. This will cause the game to crash more often.");
    }
}
