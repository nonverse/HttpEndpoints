package net.nonverse.labs.minecraft.httpendpoints;

import org.bukkit.plugin.java.JavaPlugin;

public final class HttpEndpoints extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
