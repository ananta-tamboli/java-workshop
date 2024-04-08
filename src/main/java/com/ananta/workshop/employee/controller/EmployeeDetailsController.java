package com.ananta.workshop.employee.controller;

import com.ananta.workshop.employee.entity.Employee;
import com.ananta.workshop.employee.enums.Department;
import com.ananta.workshop.employee.service.EmployeeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controller class for handling HTTP requests related to employee details.
 */
@RestController
@RequestMapping("api/v1/employee")
public class EmployeeDetailsController {
    @Autowired
    EmployeeDetailsService service;

    /**
     * Retrieves all employees.
     * @return ResponseEntity containing a list of all employees and HTTP status 200 (OK).
     */
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = service.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    /**
     * Retrieves an employee by their ID.
     * @param id The ID of the employee to retrieve.
     * @return ResponseEntity containing the employee with the specified ID and HTTP status 200 (OK),
     *         or HTTP status 404 (Not Found) if the employee is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = service.getEmployeeById(id);
        return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Saves a new employee.
     * @param employee The employee to save.
     * @return ResponseEntity containing the saved employee and HTTP status 201 (Created).
     */
    @PostMapping("/save")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = service.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    /**
     * Updates an existing employee.
     * @param id The ID of the employee to update.
     * @param employeeDetails The updated details of the employee.
     * @return ResponseEntity containing the updated employee and HTTP status 200 (OK),
     *         or HTTP status 404 (Not Found) if the employee is not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Optional<Employee> existingEmployee = service.getEmployeeById(id);
        if (existingEmployee.isPresent()) {
            Employee updatedEmployee = service.saveEmployee(employeeDetails);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Deletes an employee by their ID.
     * @param id The ID of the employee to delete.
     * @return ResponseEntity with HTTP status 204 (No Content) if the employee is successfully deleted,
     *         or HTTP status 404 (Not Found) if the employee is not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id) {
        if (service.getEmployeeById(id).isPresent()) {
            service.deleteEmployeeById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // Example of using Java 8 Stream API to filter employees by a certain condition
    @GetMapping("/department/{department}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable Department department) {
        List<Employee> employees = service.getEmployeesByDepartment(department);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Example of using Java 8 Optional to handle null values
    @GetMapping("/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = service.findEmployeeById(id);
        return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Example of using Java 8 Stream API to perform calculations on employee salaries
    @GetMapping("/average-salary")
    public ResponseEntity<Double> calculateAverageSalary() {
        double averageSalary = service.calculateAverageSalary();
        return new ResponseEntity<>(averageSalary, HttpStatus.OK);
    }

    // Example of using Java 8 Stream API to sort employees by salary in descending order
    @GetMapping("/sorted-by-salary")
    public ResponseEntity<List<Employee>> getEmployeesSortedBySalaryDescending() {
        List<Employee> employees = service.getEmployeesSortedBySalaryDescending();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Example of using Java 8 Stream API to perform bulk updates
    @PutMapping("/update-salaries/{percentageIncrease}")
    public ResponseEntity<Void> updateEmployeeSalaries(@PathVariable double percentageIncrease) {
        service.updateEmployeeSalaries(percentageIncrease);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Example of using Java 8 Stream API to find the highest paid employee
    @GetMapping("/highest-paid")
    public ResponseEntity<Employee> findHighestPaidEmployee() {
        Optional<Employee> employee = service.findHighestPaidEmployee();
        return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Example of using Java 8 Stream API to count employees in each department
    @GetMapping("/count-by-department")
    public ResponseEntity<Map<Department, Long>> countEmployeesByDepartment() {
        Map<Department, Long> countByDepartment = service.countEmployeesByDepartment();
        return new ResponseEntity<>(countByDepartment, HttpStatus.OK);
    }
}
