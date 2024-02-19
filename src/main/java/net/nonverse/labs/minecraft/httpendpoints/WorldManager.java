package net.nonverse.labs.minecraft.httpendpoints;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldType;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class WorldManager {

    private final MVWorldManager manager;

    public WorldManager() {
        this.manager = ((MultiverseCore) Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("Multiverse-Core"))).getMVWorldManager();
    }

    public CompletableFuture<MultiverseWorld> createNewWorld(String name) {
        return CompletableFuture.supplyAsync(() -> {
            this.manager.addWorld(name, World.Environment.NORMAL, null, WorldType.NORMAL, true, null);
            return this.manager.getMVWorld(name);
        });
    }
}
