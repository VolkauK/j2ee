package example.demo.mapper;

import example.demo.entity.EmployeeEntity;
import example.demo.model.Employee;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class EmployeeMapper {

    public List<EmployeeEntity> modelsToEntities(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            return Collections.emptyList();
        }

        return employees.stream()
                .map(this::modelToEntity)
                .toList();
    }

    public EmployeeEntity modelToEntity(Employee employee) {
        if (employee == null) {
            return null;
        }

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(employee.getId());
        employeeEntity.setName(employee.getName());
        employeeEntity.setPosition(employee.getPosition());
        return employeeEntity;
    }

    public List<Employee> entitiesToModels(List<EmployeeEntity> employeeEntities) {
        if (employeeEntities == null || employeeEntities.isEmpty()) {
            return Collections.emptyList();
        }

        return employeeEntities.stream()
                .map(this::entityToModel)
                .toList();
    }

    public Employee entityToModel(EmployeeEntity employeeEntity) {
        if (employeeEntity == null) {
            return null;
        }

        Employee employee = new Employee();
        employee.setId(employeeEntity.getId());
        employee.setName(employeeEntity.getName());
        employee.setPosition(employeeEntity.getPosition());
        return employee;
    }
}
