package example.demo.util.normalization;

import example.demo.model.Device;
import example.demo.model.DeviceAssignment;
import example.demo.model.Employee;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDate;
import java.util.UUID;

@ApplicationScoped
public class DeviceAssignmentNormalizationComponent {

    public DeviceAssignment createDeviceAssignment(Employee employee, Device device) {
        DeviceAssignment deviceAssignment = new DeviceAssignment();
        deviceAssignment.setEmployee(employee);
        deviceAssignment.setDevice(device);
        deviceAssignment.setRequestCode(generateRequestCode(employee, device));
        deviceAssignment.setIssueDate(LocalDate.now());
        return deviceAssignment;

    }

    private String generateRequestCode(Employee employee, Device device) {
        return employee.getId() + "-" + device.getId() + "-" + UUID.randomUUID().toString();
    }
}
