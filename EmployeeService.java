package com.reethika.employeeapi.service;

import com.reethika.employeeapi.exception.DuplicateEmailException;
import com.reethika.employeeapi.exception.ResourceNotFoundException;
import com.reethika.employeeapi.model.Employee;
import com.reethika.employeeapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee) {

        employeeRepository.findByEmail(employee.getEmail())
                .ifPresent(e -> {
                    throw new DuplicateEmailException(
                            "Employee with email " +
                            employee.getEmail() +
                            " already exists"
                    );
                });

        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {

        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with id: " + id
                        )
                );
    }

    public List<Employee> getEmployeesByDepartment(String department) {
        return employeeRepository.findByDepartmentIgnoreCase(department);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {

        Employee existing = getEmployeeById(id);

        employeeRepository.findByEmail(updatedEmployee.getEmail())
                .filter(e -> !e.getId().equals(id))
                .ifPresent(e -> {
                    throw new DuplicateEmailException(
                            "Email " +
                            updatedEmployee.getEmail() +
                            " is already in use"
                    );
                });

        existing.setName(updatedEmployee.getName());
        existing.setEmail(updatedEmployee.getEmail());
        existing.setDepartment(updatedEmployee.getDepartment());
        existing.setDesignation(updatedEmployee.getDesignation());
        existing.setSalary(updatedEmployee.getSalary());

        return employeeRepository.save(existing);
    }

    public void deleteEmployee(Long id) {

        Employee existing = getEmployeeById(id);

        employeeRepository.delete(existing);
    }
}
