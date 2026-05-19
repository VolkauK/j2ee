package example.demo.service;

import example.demo.entity.EmployeeEntity;
import example.demo.mapper.EmployeeMapper;
import example.demo.model.Employee;
import example.demo.repository.EmployeeRepository;
import jakarta.ejb.Stateless;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import jakarta.inject.Inject;

import java.util.Optional;

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

    public Optional<Employee> getEmployeeById(long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id);
        return Optional.ofNullable(employeeMapper.entityToModel(employeeEntity));
    }
}
