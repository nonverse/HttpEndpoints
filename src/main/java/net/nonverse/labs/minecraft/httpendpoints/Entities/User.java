package net.nonverse.labs.minecraft.httpendpoints.Entities;

import java.util.UUID;

public class User {

    private final UUID uuid;

    public User(String uuid) {
        this.uuid = UUID.fromString(uuid);
    }

    public UUID getUuid() {
        return this.uuid;
    }
}
