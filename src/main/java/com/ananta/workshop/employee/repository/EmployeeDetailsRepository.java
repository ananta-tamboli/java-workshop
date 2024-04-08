package com.ananta.workshop.employee.repository;

import com.ananta.workshop.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repository interface for accessing and managing Employee entities in the database.
 * Extends JpaRepository to inherit basic CRUD operations and querying functionalities.
 */
@Repository
public interface EmployeeDetailsRepository extends JpaRepository<Employee, Long> {
}
