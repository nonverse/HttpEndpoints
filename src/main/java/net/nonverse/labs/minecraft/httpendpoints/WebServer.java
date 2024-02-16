package net.nonverse.labs.minecraft.httpendpoints;

import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.Handler;
import io.javalin.http.HandlerType;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.logging.Logger;

public class WebServer {

    private final int port;
    private final Logger log;
    private final Javalin javalin;

    public WebServer(FileConfiguration bukkitConfig, Logger log) {
        this.port = bukkitConfig.getInt("port");
        this.log = log;
        
        this.javalin = Javalin.create(config -> configureJavalin(config));
    }

    private void configureJavalin(JavalinConfig config) {
        config.http.defaultContentType = "application/json";
        config.showJavalinBanner = false;
    }

    public void get(String route, Handler handler) {
        this.javalin.addHttpHandler(HandlerType.GET, route, handler);
    }
    public void post(String route, Handler handler) {
        this.javalin.addHttpHandler(HandlerType.POST, route, handler);
    }

    public void start() {
        this.javalin.start(this.port);
    }

    public void stop() {
        this.javalin.stop();
    }

    public Javalin getJavalin() {
        return this.javalin;
    }
}
