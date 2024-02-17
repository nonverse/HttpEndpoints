package net.nonverse.labs.minecraft.httpendpoints.Api.Middleware;

import com.auth0.jwt.interfaces.DecodedJWT;
import io.javalin.http.Context;
import io.javalin.http.UnauthorizedResponse;
import net.nonverse.labs.minecraft.httpendpoints.helpers.Crypt;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;

public class Auth {

    private final Plugin plugin;

    public Auth(Plugin plugin) {
        this.plugin = plugin;
    }

    public void jwt(Context ctx) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String tokenHeader = ctx.header("Authorization");
        if (tokenHeader == null) {
            throw new UnauthorizedResponse("No Token");
        }

        String token = tokenHeader.replace("Bearer", "");
        DecodedJWT decodedToken;

        RSAPublicKey pubKey = Crypt.loadRsaKey(new File(plugin.getDataFolder(), "xs-public.key"));
    }
}
