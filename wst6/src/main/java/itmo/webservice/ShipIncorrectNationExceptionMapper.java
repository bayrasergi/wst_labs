package itmo.webservice;


import itmo.webservice.exception.ShipIncorrectNationException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ShipIncorrectNationExceptionMapper implements ExceptionMapper<ShipIncorrectNationException> {

    @Override
    public Response toResponse(ShipIncorrectNationException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
    }
}
