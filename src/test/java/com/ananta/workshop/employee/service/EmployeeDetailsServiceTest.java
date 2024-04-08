package com.ananta.workshop.employee.service;

import com.ananta.workshop.employee.entity.Employee;
import com.ananta.workshop.employee.repository.EmployeeDetailsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.ananta.workshop.employee.enums.Department.CSE;
import static com.ananta.workshop.employee.enums.Department.IT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeDetailsServiceTest {
    @Mock
    private EmployeeDetailsRepository repository;

    @InjectMocks
    private EmployeeDetailsService service;

    @Test
    void testGetEmployeesByDepartment() {
        // Arrange
        List<Employee> expectedEmployees = Arrays.asList(
                new Employee(1L, "John Doe", CSE, 50000.0,null),
                new Employee(2L, "Jane Smith", CSE, 60000.0, 1L)
        );
        when(repository.findAll()).thenReturn(expectedEmployees);

        // Act
        List<Employee> result = service.getEmployeesByDepartment(CSE);

        // Assert
        assertEquals(expectedEmployees.size(), result.size());
        assertTrue(result.containsAll(expectedEmployees));
    }

    @Test
    void testFindEmployeeById_ExistingId() {
        // Arrange
        Long id = 1L;
        Employee expectedEmployee = new Employee(id, "John Doe", IT, 50000.0,null);
        when(repository.findById(id)).thenReturn(Optional.of(expectedEmployee));

        // Act
        Optional<Employee> result = service.findEmployeeById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedEmployee, result.get());
    }

    @Test
    void testFindEmployeeById_NonExistingId() {
        // Arrange
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<Employee> result = service.findEmployeeById(id);

        // Assert
        assertTrue(result.isEmpty());
    }

    // Additional tests for other methods can be added similarly
}
