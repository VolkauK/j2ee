package example.demo.service;

import example.demo.entity.DeviceAssignmentEntity;
import example.demo.exception.DeviceNotFountException;
import example.demo.exception.EmployeeNotFoundExcetion;
import example.demo.mapper.DeviceAssignmentMapper;
import example.demo.model.Device;
import example.demo.model.DeviceAssignment;
import example.demo.model.Employee;
import example.demo.repository.DeviceAssignmentRepository;
import example.demo.util.normalization.DeviceAssignmentNormalizationComponent;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Stateless
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED, force = true)
public class DeviceAssignmentService {

    @NonNull
    private final DeviceService deviceService;

    @NonNull
    private final EmployeeService employeeService;

    @NonNull
    private final DeviceAssignmentMapper deviceAssignmentMapper;

    @NonNull
    private final DeviceAssignmentRepository deviceAssignmentRepository;

    @NonNull
    private final DeviceAssignmentNormalizationComponent deviceAssignmentNormalizationComponent;

    public DeviceAssignment createDeviceAssignment(long deviceId, long employeeId) {
        Device device = deviceService.getDeviceById(deviceId)
                .orElseThrow(() -> new DeviceNotFountException("Device with id " + deviceId + " not found"));
        Employee employee = employeeService.getEmployeeById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundExcetion("Employee with id " + employeeId + " not found"));

        DeviceAssignment deviceAssignment = deviceAssignmentNormalizationComponent.createDeviceAssignment(employee, device);
        DeviceAssignmentEntity deviceAssignmentEntity = deviceAssignmentMapper.modelToEntity(deviceAssignment);
        deviceAssignmentRepository.create(deviceAssignmentEntity);
        return deviceAssignmentMapper.entityToModel(deviceAssignmentEntity);
    }
}
