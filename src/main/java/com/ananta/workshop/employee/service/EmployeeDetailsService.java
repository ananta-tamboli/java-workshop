package com.ananta.workshop.employee.service;

import com.ananta.workshop.employee.entity.Employee;
import com.ananta.workshop.employee.enums.Department;
import com.ananta.workshop.employee.repository.EmployeeDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for handling business logic related to Employee details.
 */
@Service
public class EmployeeDetailsService {
    private final EmployeeDetailsRepository repository;

    /**
     * Constructor for EmployeeDetailsService.
     *
     * @param repository An instance of EmployeeDetailsRepository for accessing Employee data.
     */
    @Autowired
    public EmployeeDetailsService(EmployeeDetailsRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves all employees from the database.
     *
     * @return A list of all employees.
     */
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    /**
     * Retrieves an employee by their ID.
     *
     * @param id The ID of the employee to retrieve.
     * @return The employee with the specified ID, or null if not found.
     */
    public Optional<Employee> getEmployeeById(Long id) {
        return repository.findById(id);
    }

    /**
     * Saves or updates an employee in the database.
     *
     * @param employee The employee to save or update.
     * @return The saved or updated employee.
     */
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    /**
     * Deletes an employee from the database by their ID.
     *
     * @param id The ID of the employee to delete.
     */
    public void deleteEmployeeById(Long id) {
        repository.deleteById(id);
    }
    // These examples demonstrate the usage of Java 8 features like
    // Streams, Optional, and lambdas to perform common tasks such as
    // filtering, mapping, sorting, and aggregating data in a concise and efficient manner.
    // note: some operations could be more efficient by writing queries or using derived queries

    // Example of using Java 8 Stream API to filter employees by a certain condition
    public List<Employee> getEmployeesByDepartment(Department department) {
        return repository.findAll().stream()
                .filter(employee -> employee.getDepartment().equals(department))
                .collect(Collectors.toList());
    }

    // Example of using Java 8 Optional to handle null values
    public Optional<Employee> findEmployeeById(Long id) {
        return repository.findById(id);
    }

    // Example of using Java 8 Stream API to perform calculations on employee salaries
    public double calculateAverageSalary() {
        return repository.findAll().stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0); // Default value if no employees are found
    }

    // Example of using Java 8 Stream API to sort employees by salary in descending order
    public List<Employee> getEmployeesSortedBySalaryDescending() {
        return repository.findAll().stream()
                .sorted((emp1, emp2) -> Double.compare(emp2.getSalary(), emp1.getSalary()))
                .collect(Collectors.toList());
    }

    // Example of using Java 8 Stream API to perform bulk updates
    public void updateEmployeeSalaries(double percentageIncrease) {
        repository.findAll().forEach(employee -> {
            double increasedSalary = employee.getSalary() * (1 + percentageIncrease / 100);
            employee.setSalary(increasedSalary);
            repository.save(employee);
        });
    }

    // Example of using Java 8 Stream API to find the highest paid employee
    public Optional<Employee> findHighestPaidEmployee() {
        return repository.findAll().stream()
                .max(Comparator.comparingDouble(Employee::getSalary));
    }

    // Example of using Java 8 Stream API to count employees in each department
    public Map<Department, Long> countEmployeesByDepartment() {
        return repository.findAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
    }
}
