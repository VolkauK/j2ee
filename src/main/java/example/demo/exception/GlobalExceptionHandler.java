package example.demo.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static java.util.Map.entry;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<RuntimeException> {

    private static final String APPLICATION_JSON = "application/json";

    private final Map<Class<? extends RuntimeException>, Function<RuntimeException, Response>> handlers = Map.ofEntries(
            entry(DeviceNotFountException.class, e -> handleNotFountException()),
            entry(EmployeeNotFoundExcetion.class, e -> handleNotFountException())
    );

    @Override
    public Response toResponse(RuntimeException exception) {
        Function<RuntimeException, Response> exceptionHandler = handlers.get(exception.getClass());

        if (Objects.nonNull(exceptionHandler)) {
            return exceptionHandler.apply(exception);
        }
        return handleGenericError(exception);
    }

    private Response handleNotFountException() {
        return Response.status(Response.Status.NOT_FOUND)
                .type(APPLICATION_JSON)
                .build();
    }

    private Response handleGenericError(RuntimeException e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(Map.of("error", "Server error", "details", e.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

}
