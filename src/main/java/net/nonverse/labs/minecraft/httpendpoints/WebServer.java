package net.nonverse.labs.minecraft.httpendpoints;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.logging.Logger;

public class WebServer {
    
    private final int port;
    private final Logger log;

    public WebServer(FileConfiguration config, Logger log) {
        this.port = config.getInt("port");
        this.log = log;
    }
}
