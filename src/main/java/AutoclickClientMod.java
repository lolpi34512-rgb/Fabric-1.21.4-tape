package com.autoclick;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public class AutoclickClientMod implements ClientModInitializer {
    private static boolean enabled = false;

    @Override
    public void onInitializeClient() {

        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                ClientCommandManager.literal("clikk")
                    .executes(context -> {
                        enabled = !enabled;
                        ClientPlayerEntity p = MinecraftClient.getInstance().player;
                        if (p != null) {
                            p.sendMessage(Text.literal("Autoclick: " + (enabled ? "ON" : "OFF")));
                        }
                        return 1;
                    })
            );
        });

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
                if (enabled) {
                    MinecraftClient client = MinecraftClient.getInstance();
                    if (client.player != null) {
                        client.doItemUse();
                    }
                }
            }
        }).start();
    }
}
