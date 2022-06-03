package com.example.sma.presentation.employee;

import com.example.sma.application.employee.EmployeeApplicationService;
import com.example.sma.domain.models.employee.Employee;
import com.example.sma.domain.models.employee.Security;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeApplicationService employeeApplicationService;

    @GetMapping
    public List<Employee> findAllEmployee(){
        return  employeeApplicationService.findAllEmployee();
    }

    @GetMapping("/security")
    public List<Security> findAllSecurity(){
        return employeeApplicationService.findAllSecurity();
    }

}
