package net.nonverse.labs.minecraft.httpendpoints.Api.Middleware;

import org.bukkit.plugin.Plugin;

public class MiddlewareInitializer {
    private final Auth authMiddleware;

    public MiddlewareInitializer(Plugin plugin) {
        this.authMiddleware = new Auth(plugin);
    }

    public Auth auth() {
        return this.authMiddleware;
    }
}
