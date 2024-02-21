package net.nonverse.labs.minecraft.httpendpoints.Api;

public class ApiInitializer {
    private final WorldApi worldApi;

    public ApiInitializer() {
        // Instantiate api handlers
        this.worldApi = new WorldApi();
    }

    public WorldApi worldApi() {
        return this.worldApi;
    }
}
