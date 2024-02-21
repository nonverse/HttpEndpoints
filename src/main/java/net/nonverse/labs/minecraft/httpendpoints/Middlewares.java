package net.nonverse.labs.minecraft.httpendpoints;

import net.nonverse.labs.minecraft.httpendpoints.Api.Middleware.MiddlewareInitializer;
import org.bukkit.plugin.Plugin;

import java.util.logging.Logger;

public final class Middlewares {

    public static void registerMiddlewares(WebServer server, Logger log, Plugin plugin) {
        MiddlewareInitializer middlewares = new MiddlewareInitializer(plugin);

        // Player message routes
        server.middleware("player/message", middlewares.auth()::online);

        // Player world routs
        server.middleware("player/world/*", middlewares.auth()::user);

        // Root
        server.middleware("*", middlewares.auth()::jwt);
    }
}