package example.demo.controller;

import example.demo.model.DeviceAssignment;
import example.demo.service.DeviceAssignmentService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;

@Path("/hello-world")
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class HelloController {

    @NonNull
    private final DeviceAssignmentService deviceAssignmentService;

    @POST
    @Produces("application/json")
    public DeviceAssignment assignDevice(long deviceId, long employeeId) {
        return deviceAssignmentService.createDeviceAssignment(deviceId, employeeId);
    }
}