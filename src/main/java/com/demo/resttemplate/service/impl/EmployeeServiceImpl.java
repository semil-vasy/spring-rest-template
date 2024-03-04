package com.demo.resttemplate.service.impl;

import com.demo.resttemplate.dto.EmployeeDto;
import com.demo.resttemplate.exception.ResourceNotFoundException;
import com.demo.resttemplate.model.Employee;
import com.demo.resttemplate.repository.EmployeeRepository;
import com.demo.resttemplate.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = this.employeeRepository.findAll();
        return employees.stream().map((emp) -> this.modelMapper.map(emp, EmployeeDto.class)).toList();
    }

    @Override
    public EmployeeDto getEmployeeById(long employeeId) {
        Employee employee = this.employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("No employee found of id : " + employeeId));
        return this.modelMapper.map(employee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto addOrUpdateEmployee(EmployeeDto employeeDto, Long employeeId) {
        if (employeeId != null) {
            Employee employee = this.employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new ResourceNotFoundException("No employee found of id : " + employeeId));
            employeeDto.setId(employeeId);
        }

        Employee savedEmployee = this.employeeRepository.save(this.modelMapper.map(employeeDto, Employee.class));
        return this.modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public void deleteEmployee(long employeeId) {
        Employee employee = this.employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("No employee found of id : " + employeeId));
        this.employeeRepository.delete(employee);
    }
}
