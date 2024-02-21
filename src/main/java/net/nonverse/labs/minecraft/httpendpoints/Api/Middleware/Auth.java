package net.nonverse.labs.minecraft.httpendpoints.Api.Middleware;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;
import io.javalin.http.UnauthorizedResponse;
import net.nonverse.labs.minecraft.httpendpoints.helpers.Crypt;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

public class Auth {

    private final Plugin plugin;

    public Auth(Plugin plugin) {
        this.plugin = plugin;
    }

    public void jwt(Context ctx) {
        this.validateJwt(ctx);
    }

    public void user(Context ctx) {
        DecodedJWT decodedJWT = this.validateJwt(ctx);

        if (decodedJWT.getSubject().isEmpty()) {
            throw new ForbiddenResponse("No user present in request");
        }

        ctx.attribute("user", Bukkit.getOfflinePlayer(UUID.fromString(decodedJWT.getSubject())).isOnline() ? Bukkit.getPlayer(UUID.fromString(decodedJWT.getSubject())) : Bukkit.getOfflinePlayer(UUID.fromString(decodedJWT.getSubject())));
    }

    public void online(Context ctx) {
        DecodedJWT decodedJWT = this.validateJwt(ctx);

        if (decodedJWT.getSubject().isEmpty()) {
            throw new ForbiddenResponse("No user present in request");
        }

        if (!Bukkit.getOfflinePlayer(UUID.fromString(decodedJWT.getSubject())).isOnline()) {
            throw new ForbiddenResponse("Player is not online");
        }

        ctx.attribute("user", Bukkit.getPlayer(UUID.fromString(decodedJWT.getSubject())));
    }

    private DecodedJWT validateJwt(Context ctx) {
        String tokenHeader = ctx.header("Authorization");
        if (tokenHeader == null) {
            throw new UnauthorizedResponse();
        }

        String token = tokenHeader.replace("Bearer ", "");
        DecodedJWT decodedToken;

        try {
            RSAPublicKey pubKey = Crypt.loadRsaKey(new File(plugin.getDataFolder(), "xs-public.key"));

            Algorithm algorithm = Algorithm.RSA256(pubKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(plugin.getConfig().getString("api"))
                    .withAudience(plugin.getConfig().getString("host"))
                    .withClaim("ttp", "api:xs")
                    .build();

            return verifier.verify(token);

        } catch (Exception e) {
            throw new UnauthorizedResponse();
        }
    }
}
