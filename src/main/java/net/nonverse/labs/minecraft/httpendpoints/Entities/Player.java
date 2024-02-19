package net.nonverse.labs.minecraft.httpendpoints.Entities;

import net.kyori.adventure.text.Component;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.node.types.PermissionNode;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class Player {

    private final UUID uuid;
    private final org.bukkit.entity.Player bukkitPlayer;

    public Player(String uuid) {
        this.uuid = UUID.fromString(uuid);
        this.bukkitPlayer = Bukkit.getPlayer(this.uuid);
    }

    public UUID getUniqueId() {
        return this.uuid;
    }

    public String getUsername() {
        return this.bukkitPlayer.getName();
    }

    public void sendMessage(@NotNull Component message) {
        this.bukkitPlayer.sendMessage(message);
    }

    public CompletableFuture<Void> addPermission(@NotNull String permissionNode) {
        LuckPerms luckpermsApi = Objects.requireNonNull(Bukkit.getServicesManager().getRegistration(LuckPerms.class)).getProvider();
        return CompletableFuture.supplyAsync(() -> {
            luckpermsApi.getUserManager().modifyUser(this.uuid, user -> {
                user.data().add(PermissionNode.builder(permissionNode).build());
            });
            return null;
        });
    }
}
