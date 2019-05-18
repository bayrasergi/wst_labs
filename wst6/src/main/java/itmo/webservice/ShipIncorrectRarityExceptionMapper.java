package itmo.webservice;

import itmo.webservice.exception.ShipIncorrectRarityException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ShipIncorrectRarityExceptionMapper implements ExceptionMapper<ShipIncorrectRarityException> {
    @Override
    public Response toResponse(ShipIncorrectRarityException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}
