package net.nonverse.labs.minecraft.httpendpoints;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class HttpEndpoints extends JavaPlugin {

    private WebServer server;

    private static final Logger log = Bukkit.getLogger();
    public static HttpEndpoints instance = null;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        startWebServer(getConfig());
        instance = this;
    }

    @Override
    public void onDisable() {
        server.stop();
    }

    /**
     * Start the webserver and configure all routes and middlewares
     *
     * @param bukkitConfig
     */
    private void startWebServer(FileConfiguration bukkitConfig) {
        WebServer server = new WebServer(bukkitConfig, log);

        server.start();

        Routes.registerRoutes(server, log);
        Middlewares.registerMiddlewares(server, log, this);
    }

    public static HttpEndpoints getInstance() {
        return instance;
    }
}
