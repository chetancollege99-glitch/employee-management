package com.employee.management.service;

import com.employee.management.model.Employee;
import com.employee.management.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void addEmployee_shouldSaveEmployee() {

        Employee employee = new Employee(
                1L,
                "Chetan",
                "chetan@example.com",
                "DevOps"
        );

        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee result = employeeService.addEmployee(employee);

        assertEquals(employee, result);
    }
}