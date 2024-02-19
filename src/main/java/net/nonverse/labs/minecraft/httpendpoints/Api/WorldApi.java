package net.nonverse.labs.minecraft.httpendpoints.Api;

import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import io.javalin.http.Context;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.node.types.PermissionNode;
import net.nonverse.labs.minecraft.httpendpoints.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class WorldApi {
    private final WorldManager manager;
    private final RegisteredServiceProvider<LuckPerms> luckperms;

    public WorldApi() {
        this.manager = new WorldManager();
        this.luckperms = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
    }

    public void createWorld(@NotNull Context ctx) {
        LuckPerms luckpermsApi = this.luckperms.getProvider();

        String worldName = ctx.pathParamAsClass("world_id", String.class).get();

        CompletableFuture<MultiverseWorld> world = this.manager.createNewWorld(worldName);

        // TODO Add to thenAccept() of world
        luckpermsApi.getUserManager().modifyUser(Objects.requireNonNull(ctx.attribute("uuid")), user -> {
            user.data().add(PermissionNode.builder("multiverse.access." + worldName).build());
        });
    }
}
