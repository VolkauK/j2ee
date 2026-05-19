package example.demo.service;

import example.demo.entity.DeviceAssignmentEntity;
import example.demo.mapper.DeviceAssignmentMapper;
import example.demo.model.Device;
import example.demo.model.DeviceAssignment;
import example.demo.model.Employee;
import example.demo.repository.DeviceAssignmentRepository;
import example.demo.util.normalization.DeviceAssignmentNormalizationComponent;
import jakarta.ejb.Stateless;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;

@Stateless
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class DeviceAssignmentService {

    @NonNull
    private final DeviceAssignmentNormalizationComponent deviceAssignmentNormalizationComponent;

    @NonNull
    private final DeviceService deviceService;

    @NonNull
    private final EmployeeService employeeService;

    @NonNull
    private final DeviceAssignmentRepository deviceAssignmentRepository;
    private DeviceAssignmentMapper deviceAssignmentMapper;

    public DeviceAssignment createDeviceAssignment(long deviceId, long employeeId) {
        Device device = deviceService.getDeviceById(deviceId);
        Employee employee = employeeService.getEmployeeById(employeeId);

        if (device == null || employee == null) {
            throw new IllegalArgumentException("Device or Employee not found");
        }

        DeviceAssignment deviceAssignment = deviceAssignmentNormalizationComponent.createDeviceAssignment(employee, device);
        DeviceAssignmentEntity deviceAssignmentEntity = deviceAssignmentMapper.modelToEntity(deviceAssignment);
        deviceAssignmentRepository.create(deviceAssignmentEntity);
        return deviceAssignmentMapper.entityToModel(deviceAssignmentEntity);
    }


}
