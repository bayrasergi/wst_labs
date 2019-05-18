package itmo.webservice;

import itmo.webservice.exception.ShipIncorrectLevelException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ShipIncorrectLevelExceptionMapper implements ExceptionMapper<ShipIncorrectLevelException> {
    @Override
    public Response toResponse(ShipIncorrectLevelException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}
