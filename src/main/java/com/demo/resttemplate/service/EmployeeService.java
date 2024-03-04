package com.demo.resttemplate.service;

import com.demo.resttemplate.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> getAllEmployees();

    EmployeeDto getEmployeeById(long employeeId);

    EmployeeDto addOrUpdateEmployee(EmployeeDto employeeDto, Long employeeId);

    void deleteEmployee(long employeeId);

}
