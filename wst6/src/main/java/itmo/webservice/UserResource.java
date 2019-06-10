package itmo.webservice;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/users")
public class UserResource {
    @POST
    public int createUser(User user) {
        return new PostgresSQLDAO().createUser(user);
    }
}
