package example.demo.mapper;

import example.demo.entity.DeviceAssignmentEntity;
import example.demo.model.DeviceAssignment;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor_ = @Inject)
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED, force = true)
public class DeviceAssignmentMapper {

    @NonNull
    private final DeviceMapper deviceMapper;

    @NonNull
    private final EmployeeMapper employeeMapper;

    public DeviceAssignment entityToModel(DeviceAssignmentEntity entity) {
        if (entity == null) {
            return null;
        }

        DeviceAssignment deviceAssignment = new DeviceAssignment();
        deviceAssignment.setId(entity.getId());
        deviceAssignment.setRequestCode(entity.getRequestCode());
        deviceAssignment.setIssueDate(entity.getIssueDate());
        deviceAssignment.setDevice(deviceMapper.entityToModel(entity.getDevice()));
        deviceAssignment.setEmployee(employeeMapper.entityToModel(entity.getEmployee()));
        return deviceAssignment;
    }

    public DeviceAssignmentEntity modelToEntity(DeviceAssignment model) {
        DeviceAssignmentEntity entity = new DeviceAssignmentEntity();
        entity.setId(model.getId());
        entity.setRequestCode(model.getRequestCode());
        entity.setIssueDate(model.getIssueDate());
        entity.setDevice(deviceMapper.modelToEntity(model.getDevice()));
        entity.setEmployee(employeeMapper.modelToEntity(model.getEmployee()));
        return entity;
    }
}
