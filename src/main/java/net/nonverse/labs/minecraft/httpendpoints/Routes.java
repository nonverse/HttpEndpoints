package net.nonverse.labs.minecraft.httpendpoints;

import net.nonverse.labs.minecraft.httpendpoints.Api.ApiInitializer;

import java.util.logging.Logger;

public final class Routes {

    public static void registerRoutes(WebServer server, Logger log) {
        ApiInitializer api = new ApiInitializer();

        // Player routes
        server.post("player/world", api.worldApi()::createWorld);
    }
}
