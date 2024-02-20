package net.nonverse.labs.minecraft.httpendpoints;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class WorldManager {

    private final String worldContainer;

    public WorldManager() {
        this.worldContainer = Bukkit.getWorldContainer().getPath();
    }

    public CompletableFuture<Void> createWorld(String name) {

        return CompletableFuture.runAsync(() -> {
            try {
                FileUtils.copyDirectory(new File(this.worldContainer + "/world_template"), new File(worldContainer + "/" + name));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public CompletableFuture<Void> deleteWorld(String name) {
        return CompletableFuture.runAsync(() -> {
            try {
                FileUtils.deleteDirectory(new File(this.worldContainer + "/" + name));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public CompletableFuture<Void> resetWorld(String name) {
        return CompletableFuture.runAsync(() -> {
            try {
                FileUtils.deleteDirectory(new File(this.worldContainer + "/" + name));
                FileUtils.copyDirectory(new File(this.worldContainer + "/world_template"), new File(worldContainer + "/" + name));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void loadWorld(String name) {
        new WorldCreator(name).createWorld();
    }

    public void unloadWorld(String name) {
        Bukkit.getServer().unloadWorld(name, true);
    }

    public void reloadWorld(String name) {
        Bukkit.getServer().unloadWorld(name, true);
        new WorldCreator(name).createWorld();
    }
}
