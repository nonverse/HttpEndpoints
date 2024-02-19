package net.nonverse.labs.minecraft.httpendpoints;

import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class WorldManager {

    public WorldManager() {
        //
    }

    public CompletableFuture<Void> createNewWorld(String name) {
        String worldContainer = Bukkit.getWorldContainer().getPath();

        return CompletableFuture.runAsync(() -> {
            try {
                FileUtils.copyDirectory(new File(worldContainer + "/world_template"), new File(worldContainer + "/" + name));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void loadWorld(String name) {
        new WorldCreator(name).createWorld();
    }
}
