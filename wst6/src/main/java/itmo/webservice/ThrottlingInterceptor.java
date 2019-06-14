package itmo.webservice;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import itmo.webservice.exception.ThrottlingException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.concurrent.atomic.AtomicInteger;

@Provider
public class ThrottlingInterceptor implements ContainerRequestFilter, ContainerResponseFilter,
        ExceptionMapper<ThrottlingException> {

    private final int MAX_USERS_COUNT = 1;

    private AtomicInteger usersCount;

    public ThrottlingInterceptor() {
        usersCount = new AtomicInteger(0);
    }

    @Override
    public ContainerRequest filter(ContainerRequest request) {
        if (usersCount.incrementAndGet() > MAX_USERS_COUNT) {
            throw ThrottlingException.DEFAULT_INSTANCE;
        }
        return request;
    }

    @Override
    public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
        usersCount.decrementAndGet();
        return response;
    }

    @Override
    public Response toResponse(ThrottlingException exception) {
        return Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
    }
}
