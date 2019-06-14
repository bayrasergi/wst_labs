package itmo.webservice;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;

@Provider
public class AuthenticationRequestFilter implements ContainerRequestFilter {

    private User getUser(String baseAuth) {
        String usernamePass = baseAuth.replaceFirst("Basic ", "");
        String[] userInfo = new String(Base64.getDecoder().decode(usernamePass)).split(":");
        try {
            String encodedPass = new String(MessageDigest.getInstance("SHA-256").digest(
                    userInfo[1].getBytes(StandardCharsets.UTF_8)));
            return new PostgresSQLDAO().getUser(userInfo[0], encodedPass);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ContainerRequest filter(ContainerRequest containerRequest) {
        String authentication = containerRequest.getHeaderValue("Authorization");
        User user = getUser(authentication);
        if (Objects.nonNull(user)) {
            return containerRequest;
        }
        Response.ResponseBuilder builder;
        String response = "User not found!";
        builder = Response.status(Response.Status.UNAUTHORIZED).entity(response);
        throw new WebApplicationException(builder.build());
    }
}
