package net.nonverse.labs.minecraft.httpendpoints;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class HttpEndpoints extends JavaPlugin {

    private WebServer server;

    private static final Logger log = Bukkit.getLogger();

    @Override
    public void onEnable() {
        saveDefaultConfig();

        FileConfiguration bukkitConfig = getConfig();

        WebServer server = new WebServer(bukkitConfig, log);
        server.start();
        Routes.registerRoutes(server, log);
    }

    @Override
    public void onDisable() {
        server.stop();
    }
}
