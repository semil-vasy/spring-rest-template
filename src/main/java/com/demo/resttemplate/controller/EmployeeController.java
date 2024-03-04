package com.demo.resttemplate.controller;

import com.demo.resttemplate.dto.ApiResponse;
import com.demo.resttemplate.dto.EmployeeDto;
import com.demo.resttemplate.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    private List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("{id}")
    private EmployeeDto getEmployeeById(@PathVariable("id") int employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping()
    private EmployeeDto createEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.addOrUpdateEmployee(employeeDto, null);
    }

    @PutMapping("{id}")
    private EmployeeDto updateEmployee(@PathVariable("id") long employeeId, @RequestBody EmployeeDto employeeDto) {
        return employeeService.addOrUpdateEmployee(employeeDto, employeeId);
    }

    @DeleteMapping("{id}")
    private ApiResponse deleteById(@PathVariable("id") int employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ApiResponse(200, true, "Employee deleted successfully");
    }
}