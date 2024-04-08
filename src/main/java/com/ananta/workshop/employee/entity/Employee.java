package com.ananta.workshop.employee.entity;

import com.ananta.workshop.employee.enums.Department;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Enumerated(EnumType.STRING)
    Department department;
    Double salary;
    Long reportsTo;
}
