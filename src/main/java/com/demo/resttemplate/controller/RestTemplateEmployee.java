package com.demo.resttemplate.controller;

import com.demo.resttemplate.dto.ApiResponse;
import com.demo.resttemplate.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@RestController
@RequestMapping("api/v2/employees")
public class RestTemplateEmployee {

    private final String URI_EMPLOYEE = "http://localhost:8080/api/employees";
    private final String URI_EMPLOYEE_ID = "http://localhost:8080/api/employees/";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<EmployeeDto[]> getAllEmployee() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(URI_EMPLOYEE, HttpMethod.GET, entity, EmployeeDto[].class);
    }

    @GetMapping("{id}")
    public Object getEmployeeById(@PathVariable final Integer id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(URI_EMPLOYEE_ID + id, HttpMethod.GET, entity, EmployeeDto.class);
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createV2(@RequestBody final EmployeeDto newEmployee) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<EmployeeDto> entity = new HttpEntity<>(newEmployee, httpHeaders);
        return restTemplate.exchange(URI_EMPLOYEE, HttpMethod.POST, entity, EmployeeDto.class);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployeeV2(@PathVariable final Integer id, @RequestBody EmployeeDto newEmployee) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<EmployeeDto> entity = new HttpEntity<>(newEmployee, httpHeaders);
        return restTemplate.exchange(URI_EMPLOYEE_ID + id, HttpMethod.PUT, entity, EmployeeDto.class);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteV2(@PathVariable final Integer id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<ApiResponse> entity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange(URI_EMPLOYEE_ID + id, HttpMethod.DELETE, entity, ApiResponse.class);
    }

}
