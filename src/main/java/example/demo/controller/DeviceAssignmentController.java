package example.demo.controller;

import example.demo.model.Device;
import example.demo.model.DeviceAssignment;
import example.demo.model.Employee;
import example.demo.service.DeviceAssignmentService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Path("/device/assignment")
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class DeviceAssignmentController {

    @NonNull
    private final DeviceAssignmentService deviceAssignmentService;

    @GET
    @Produces("application/json")
    public List<DeviceAssignment> getDeviceAssignments() {
        Device device = new Device();
        device.setId(1L);
        device.setSerialNumber("SN123456");

        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("Jake");

        DeviceAssignment deviceAssignment = new DeviceAssignment();
        deviceAssignment.setDevice(device);
        deviceAssignment.setEmployee(employee);
        deviceAssignment.setRequestCode("REQ123456");
        deviceAssignment.setIssueDate(LocalDate.now());
        return List.of(deviceAssignment);
    }

    @POST
    @Produces("application/json")
    public DeviceAssignment assignDevice(long deviceId, long employeeId) {
        return deviceAssignmentService.createDeviceAssignment(deviceId, employeeId);
    }
}