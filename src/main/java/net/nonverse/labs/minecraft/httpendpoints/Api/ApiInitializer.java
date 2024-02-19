package net.nonverse.labs.minecraft.httpendpoints.Api;

public class ApiInitializer {

    private final ChatApi chatApi;
    private final PlayerWorldApi worldApi;

    public ApiInitializer() {
        // Instantiate api handlers
        this.chatApi = new ChatApi();
        this.worldApi = new PlayerWorldApi();
    }

    public ChatApi chatApi() {
        return this.chatApi;
    }

    public PlayerWorldApi playerWorldApi() {
        return this.worldApi;
    }
}
