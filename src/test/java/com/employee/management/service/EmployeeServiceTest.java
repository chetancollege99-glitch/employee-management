package com.employee.management.service;

import com.employee.management.model.Employee;
import com.employee.management.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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

    @Test
    void updateEmployee_shouldUpdateEmployee() {

        Employee existingEmployee = new Employee(
                1L,
                "Chetan",
                "chetan@example.com",
                "DevOps"
        );

        Employee updatedDetails = new Employee(
                null,
                "Chetan Reddy",
                "chetan.reddy@example.com",
                "Cloud"
        );

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(existingEmployee));

        when(employeeRepository.save(existingEmployee))
                .thenReturn(existingEmployee);

        Employee result =
                employeeService.updateEmployee(1L, updatedDetails);

        assertEquals("Chetan Reddy", result.getName());
        assertEquals("chetan.reddy@example.com", result.getEmail());
        assertEquals("Cloud", result.getDepartment());

        verify(employeeRepository).save(existingEmployee);
    }

    @Test
    void deleteEmployee_shouldDeleteEmployee() {

        Employee employee = new Employee(
                1L,
                "Chetan",
                "chetan@example.com",
                "DevOps"
        );

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        employeeService.deleteEmployee(1L);

        verify(employeeRepository).delete(employee);
    }
}