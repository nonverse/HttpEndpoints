package net.nonverse.labs.minecraft.httpendpoints;

import net.nonverse.labs.minecraft.httpendpoints.Api.Middleware.MiddlewareInitializer;
import org.bukkit.plugin.Plugin;

import java.util.logging.Logger;

public final class Middlewares {

    public static void registerMiddlewares(WebServer server, Logger log, Plugin plugin) {
        MiddlewareInitializer middlewares = new MiddlewareInitializer(plugin);

        // Register middlewares here
        server.middleware("player/*", middlewares.auth()::jwt);
    }
}