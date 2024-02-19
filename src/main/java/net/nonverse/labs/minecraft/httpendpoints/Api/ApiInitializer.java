package net.nonverse.labs.minecraft.httpendpoints.Api;

public class ApiInitializer {

    private final ChatApi chatApi;
    private final WorldApi worldApi;

    public ApiInitializer() {
        // Instantiate api handlers
        this.chatApi = new ChatApi();
        this.worldApi = new WorldApi();
    }

    public ChatApi chatApi() {
        return this.chatApi;
    }

    public WorldApi worldApi() {
        return this.worldApi;
    }
}
