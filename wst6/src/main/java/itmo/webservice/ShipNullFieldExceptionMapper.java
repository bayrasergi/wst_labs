package itmo.webservice;

import itmo.webservice.exception.ShipNullFieldException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ShipNullFieldExceptionMapper implements ExceptionMapper<ShipNullFieldException> {
    @Override
    public Response toResponse(ShipNullFieldException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}
