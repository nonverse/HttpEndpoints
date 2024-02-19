package net.nonverse.labs.minecraft.httpendpoints.Api;

import io.javalin.http.Context;
import net.kyori.adventure.text.Component;
import net.nonverse.labs.minecraft.httpendpoints.Entities.Player;
import net.nonverse.labs.minecraft.httpendpoints.WorldManager;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class WorldApi {
    private final WorldManager manager;

    public WorldApi() {
        this.manager = new WorldManager();
    }

    public void createWorld(@NotNull Context ctx) {
        Player player = Objects.requireNonNull(ctx.attribute("user"));
        String worldName = player.getUniqueId().toString();

        manager.createNewWorld(worldName)
                .thenRun(() -> {
                    manager.loadWorld(worldName); // TODO IDK if we need to load the world right away
                    player.addPermission("multiverse.access." + worldName)
                            .thenAccept(permission -> {
                                player.sendMessage(Component.text("World created"));
                                // TODO Inform API that world is ready to play on
                            });
                });
    }
}
