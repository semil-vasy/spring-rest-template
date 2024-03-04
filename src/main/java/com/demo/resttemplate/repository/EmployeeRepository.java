package com.demo.resttemplate.repository;

import com.demo.resttemplate.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
