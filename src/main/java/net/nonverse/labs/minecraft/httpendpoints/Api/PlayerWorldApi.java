package net.nonverse.labs.minecraft.httpendpoints.Api;

import io.javalin.http.Context;
import net.kyori.adventure.text.Component;
import net.nonverse.labs.minecraft.httpendpoints.Entities.Player;
import net.nonverse.labs.minecraft.httpendpoints.WorldManager;
import org.bukkit.WorldCreator;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class PlayerWorldApi {
    private final WorldManager manager;

    public PlayerWorldApi() {
        this.manager = new WorldManager();
    }

    public void createWorld(@NotNull Context ctx) {
        Player player = Objects.requireNonNull(ctx.attribute("user"));
        String worldName = ctx.pathParamAsClass("id", String.class).get();

        manager.createNewWorld(worldName)
                .thenRun(() -> {
                    new WorldCreator(worldName).createWorld();
                    player.addPermission("multiverse.access." + worldName)
                            .thenAccept(permission -> {
                                player.sendMessage(Component.text("World created"));
                                // TODO Inform API that world is ready to play on
                            });
                });
    }
}
