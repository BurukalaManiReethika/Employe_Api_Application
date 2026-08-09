package com.reethika.employeeapi;

import com.reethika.employeeapi.exception.ResourceNotFoundException;
import com.reethika.employeeapi.model.Employee;
import com.reethika.employeeapi.repository.EmployeeRepository;
import com.reethika.employeeapi.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {

        employeeRepository =
                Mockito.mock(EmployeeRepository.class);

        employeeService =
                new EmployeeService(employeeRepository);
    }

    @Test
    void getEmployeeById_returnsEmployee_whenFound() {

        Employee employee = new Employee(
                "Mani Reethika",
                "mani@example.com",
                "Engineering",
                "SDE",
                60000.0
        );

        employee.setId(1L);

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        Employee result =
                employeeService.getEmployeeById(1L);

        assertEquals(
                "Mani Reethika",
                result.getName()
        );

        verify(
                employeeRepository,
                times(1)
        ).findById(1L);
    }

    @Test
    void getEmployeeById_throwsException_whenNotFound() {

        when(employeeRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> employeeService.getEmployeeById(99L)
        );
    }

    @Test
    void createEmployee_savesSuccessfully_whenEmailIsUnique() {

        Employee employee = new Employee(
                "New Hire",
                "new@example.com",
                "HR",
                "Analyst",
                45000.0
        );

        when(employeeRepository.findByEmail("new@example.com"))
                .thenReturn(Optional.empty());

        when(employeeRepository.save(employee))
                .thenReturn(employee);

        Employee result =
                employeeService.createEmployee(employee);

        assertEquals(
                "new@example.com",
                result.getEmail()
        );

        verify(
                employeeRepository,
                times(1)
        ).save(employee);
    }
}
