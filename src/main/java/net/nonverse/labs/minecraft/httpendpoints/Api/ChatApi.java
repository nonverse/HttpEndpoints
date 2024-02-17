package net.nonverse.labs.minecraft.httpendpoints.Api;

import io.javalin.http.Context;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ChatApi {
    public void sendMessage(@NotNull Context ctx) {
        String message = Objects.requireNonNull(ctx.queryParam("message"));


    }
}
