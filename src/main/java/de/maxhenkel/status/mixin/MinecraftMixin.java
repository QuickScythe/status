package de.maxhenkel.status.mixin;

import de.maxhenkel.status.events.ClientWorldEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

    @Shadow
    public ClientLevel level;

    @Inject(method = "clearClientLevel", at = @At("HEAD"))
    private void disconnect(Screen screen, CallbackInfo info) {
        if (level != null) {
            ClientWorldEvents.DISCONNECT.invoker().run();
        }
    }

}
