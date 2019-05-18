package itmo.webservice;

import itmo.webservice.exception.ShipTypeNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ShipTypeNotFoundExceptionMapper implements ExceptionMapper<ShipTypeNotFoundException> {
    @Override
    public Response toResponse(ShipTypeNotFoundException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}
