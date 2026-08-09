package com.reethika.employeeapi.controller;

import com.reethika.employeeapi.model.Employee;
import com.reethika.employeeapi.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(
            @Valid @RequestBody Employee employee) {

        Employee saved = employeeService.createEmployee(employee);

        return new ResponseEntity<>(
                saved,
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(
            @RequestParam(required = false) String department) {

        List<Employee> employees =
                (department != null && !department.isBlank())
                        ? employeeService.getEmployeesByDepartment(department)
                        : employeeService.getAllEmployees();

        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                employeeService.getEmployeeById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody Employee employee) {

        return ResponseEntity.ok(
                employeeService.updateEmployee(id, employee)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }
}
