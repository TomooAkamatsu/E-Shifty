package com.example.sma.application.employee;

import com.example.sma.domain.models.employee.Employee;
import com.example.sma.domain.models.employee.Security;
import com.example.sma.infrastructure.employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeApplicationService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> findAllEmployee(){

        return employeeRepository.findAllEmployee();
    }

    public List<Security> findAllSecurity() {
        System.out.println("test");
        return employeeRepository.findAllSecurity();
    }
}
