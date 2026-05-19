package example.demo.service;

import example.demo.entity.EmployeeEntity;
import example.demo.mapper.EmployeeMapper;
import example.demo.model.Employee;
import example.demo.repository.EmployeeRepository;
import jakarta.ejb.Stateless;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;

@Stateless
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class EmployeeService {

    @NonNull
    private final EmployeeMapper employeeMapper;
    @NonNull
    private final EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = employeeMapper.modelToEntity(employee);
        employeeRepository.create(employeeEntity);
        return employeeMapper.entityToModel(employeeEntity);
    }

    public Employee getEmployeeById(long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id);
        return employeeMapper.entityToModel(employeeEntity);
    }
}
