package net.nonverse.labs.minecraft.httpendpoints.Api;

import io.javalin.http.Context;
import net.kyori.adventure.text.Component;
import net.nonverse.labs.minecraft.httpendpoints.WorldManager;
import org.bukkit.entity.Player;
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

        manager.createWorld(worldName)
                .thenRun(() -> {
                    manager.loadWorld(player.getUniqueId().toString()); // TODO IDK if we need to load the world right away
                });
    }
}
