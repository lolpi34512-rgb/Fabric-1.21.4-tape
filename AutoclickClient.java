package com.autoclickmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class AutoclickClient implements ClientModInitializer {
    private static boolean enabled = false;
    private int timer = 0;

    @Override
    public void onInitializeClient() {
        // Register client-side command /clikk
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("clikk").executes(ctx -> {
                enabled = !enabled;
                MinecraftClient mc = MinecraftClient.getInstance();
                if (mc.player != null) {
                    mc.player.sendMessage(Text.literal("Autoclick: " + (enabled ? "ON" : "OFF")), false);
                }
                return 1;
            }));
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (!enabled) return;
            timer++;
            if (timer >= 20) {
                timer = 0;
                MinecraftClient mc = MinecraftClient.getInstance();
                if (mc.player != null) mc.doItemUse();
            }
        });
    }
}
